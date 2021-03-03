package uk.co.innoxium.ostool.menu

abstract class MenuTool(private val aName: String) : MenuItem {

    override fun getName(): String {

        return aName
    }

    abstract fun runTool()
}