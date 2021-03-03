package uk.co.innoxium.ostool.menu

import java.io.BufferedReader
import java.io.InputStreamReader

class Menu(private val aName: String) : MenuItem {

    companion object Menu {

        @JvmStatic
        val baseMenu = Menu("Base Menu")

        @JvmStatic
        val instream = BufferedReader(InputStreamReader(System.`in`))
    }

    val menuItems = ArrayList<MenuItem>()

    override fun getName(): String {

        return aName
    }

    fun addMenuItem(subMenu: MenuItem) {

        menuItems.add(subMenu)
    }

    fun execute() {

        val input = getUserInput()
        val menuItem = menuItems[input]

        if(menuItem is uk.co.innoxium.ostool.menu.Menu) {

            println(" ***** " + menuItem.getName() + " ***** ")
            menuItem.menuItems.forEachIndexed { index, item ->

                println("  $index.  ${item.getName()}")
            }
            println(" ********************* ")

            println()
            println("Please input menu number to select: ")
            menuItem.execute()
        } else if(menuItem is MenuTool) {

            menuItem.runTool()
        }
    }

    fun getUserInput(): Int {

        val input = Integer.parseInt(instream.readLine())

        if(input > menuItems.size) {

            println("You have entered an invalid value, please try again.")
            return getUserInput()
        }

        return input
    }
}