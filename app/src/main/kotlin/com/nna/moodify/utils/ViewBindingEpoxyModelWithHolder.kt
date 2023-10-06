/*
 * Designed and developed by 2023 nna-dev
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.nna.moodify.utils

import android.view.View
import android.view.ViewParent
import androidx.viewbinding.ViewBinding
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelWithHolder
import java.lang.reflect.Method
import java.lang.reflect.ParameterizedType
import java.util.concurrent.ConcurrentHashMap

abstract class ViewBindingEpoxyModelWithHolder<in T : ViewBinding> :
    EpoxyModelWithHolder<ViewBindingHolder>() {

    @Suppress("UNCHECKED_CAST")
    override fun bind(holder: ViewBindingHolder) {
        (holder.viewBinding as T).bind()
    }

    abstract fun T.bind()

    @Suppress("UNCHECKED_CAST")
    override fun unbind(holder: ViewBindingHolder) {
        (holder.viewBinding as T).unbind()
    }

    open fun T.unbind() {}

    override fun createNewHolder(parent: ViewParent): ViewBindingHolder {
        return ViewBindingHolder(this::class.java)
    }
}

// Static cache of a method pointer for each type of item used.
private val sBindingMethodByClass = ConcurrentHashMap<Class<*>, Method>()

@Suppress("UNCHECKED_CAST")
@Synchronized
private fun getBindMethodFrom(javaClass: Class<*>): Method =
    sBindingMethodByClass.getOrPut(javaClass) {
        val actualTypeOfThis = getSuperclassParameterizedType(javaClass)
        val viewBindingClass = actualTypeOfThis.actualTypeArguments[0] as Class<ViewBinding>
        viewBindingClass.getDeclaredMethod("bind", View::class.java)
            ?: error("The binder class ${javaClass.canonicalName} should have a method bind(View)")
    }

private fun getSuperclassParameterizedType(klass: Class<*>): ParameterizedType {
    val genericSuperclass = klass.genericSuperclass
    return (genericSuperclass as? ParameterizedType)
        ?: getSuperclassParameterizedType(genericSuperclass as Class<*>)
}

class ViewBindingHolder(private val epoxyModelClass: Class<*>) : EpoxyHolder() {
    // Using reflection to get the static binding method.
    // Lazy so it's computed only once by instance, when the 1st ViewHolder is actually created.
    private val bindingMethod by lazy { getBindMethodFrom(epoxyModelClass) }

    internal lateinit var viewBinding: ViewBinding
    override fun bindView(itemView: View) {
        // The 1st param is null because the binding method is static.
        viewBinding = bindingMethod.invoke(null, itemView) as ViewBinding
    }
}
