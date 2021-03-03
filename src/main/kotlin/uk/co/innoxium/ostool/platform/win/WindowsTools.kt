package uk.co.innoxium.ostool.platform.win

import uk.co.innoxium.ostool.menu.Menu
import uk.co.innoxium.ostool.platform.win.cfg.Hibernate

class WindowsTools {

    companion object Tools {

        @JvmStatic
        fun loadMenu() {

            // Create Windows Config Menu
            val cfgMenu = Menu("Windows Configuration Menu")

            // Add items to menu
            cfgMenu.addMenuItem(Hibernate())

            // Add All Menus to Base Menu
            Menu.baseMenu.addMenuItem(cfgMenu)
        }
    }
}