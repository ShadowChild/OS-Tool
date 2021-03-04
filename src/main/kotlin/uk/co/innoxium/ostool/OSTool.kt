package uk.co.innoxium.ostool

import dyorgio.runtime.run.`as`.root.RootExecutor
import uk.co.innoxium.ostool.menu.Exit
import uk.co.innoxium.ostool.menu.ExitCodes
import uk.co.innoxium.ostool.menu.Menu
import uk.co.innoxium.ostool.menu.MenuItem
import uk.co.innoxium.ostool.platform.mac.MacTools
import uk.co.innoxium.ostool.platform.unix.UnixTools
import uk.co.innoxium.ostool.platform.win.WindowsTools
import uk.co.innoxium.swing.util.DesktopUtil
import kotlin.system.exitProcess

fun main(args: Array<String>) {

    // Load menus
    OSTool.addMenus()

    var currMenu = Menu.baseMenu

    println(" ***** " + Menu.baseMenu.getName() + " ***** ")
    Menu.baseMenu.menuItems.forEachIndexed() { index, item ->

        println("  $index.  ${item.getName()}")
    }
    println(" ********************* ")

    println()
    println("Please input menu number to select: ")

    Menu.baseMenu.execute()

    println("Press Any Key to Exit...")
    Menu.instream.readLine()
}

class OSTool {

    companion object OSTool {

        // We need to run as root to be able to run certain commands
        val rootExecutor = RootExecutor()

        @JvmStatic
        fun addMenus() {

            // Returns true if we were able to find a supported platform
            val ret = when {

                DesktopUtil.isWindows() -> {

                    WindowsTools.loadMenu()
                    true
                }
                DesktopUtil.isMac() -> {

                    MacTools.loadMenu()
                    true
                }
                DesktopUtil.isUnix() -> {

                    UnixTools.loadMenu()
                    true
                }
                else -> false
            }
            if (!ret) {

                // We are running a platform that's unsupported
                // This should never be called, but here as a failsafe
                println("OS Unsupported, aborting....")
                exitProcess(ExitCodes.OS_UNSUPPORTED.code)
            }
            Menu.baseMenu.addMenuItem(Exit())
        }

        fun printMenus(menuItem: MenuItem) {


        }
    }
}