package site.wenlong.dimens.ext

import com.intellij.ide.plugins.IdeaPluginDescriptor
import com.intellij.ide.plugins.PluginManagerCore
import com.intellij.openapi.extensions.PluginId

/**
 * 本插件信息
 *
 * @author : 郭文龙
 * @Email  : guowenlong20000@sina.com
 * @date   : 2023/5/20  1:30
 */
const val INITIAL_VERSION = "0.0.0"

const val PLUGIN_ID = "com.guowenlong.dimens"

const val VERSION_PROPERTY = "$PLUGIN_ID.version"

val descriptor: IdeaPluginDescriptor by lazy { PluginManagerCore.getPlugin(PluginId.getId(PLUGIN_ID))!! }

val version: String by lazy { descriptor.version }