from ShoppingItem import ShoppingItem
import datetime

'''
Daniel Anderson
CS521  Due 5/2/2018
Project
ShoppingListGenerator.py
'''

def getShoppingPriceList():
    """ Import the price list file as a list of dictionaries """
    infile = open("shopping_price_list.txt", "r", encoding='utf-8-sig')
    list = infile.readlines()
    price_list = []
    infile.close()
    for x in list:
        lst = x.replace('\n', '').split('; ')
        price_dict = {"Name": lst[0], "Price": float(lst[1]), "Taxable": lst[2]}
        price_list.append(price_dict)
    return price_list


def findItems(prices, str):
    """
    Search the items on the price list and return a smaller list of possible choices

    :param prices: price list of all availble items, list of dictionaries
    :param str: string that is being searched
    :return: list of possible choices from the price list that match the user's search
    """
    smallerList = []
    for i in prices:
        if( i["Name"].lower().find(str.lower()) != -1):
            smallerList.append(i)
    return smallerList


def displayList(myList):
    """
    Returns a string of the list and displays the list to the console.

    :param myList: list of ShoppingItems chosen by the user
    :return: string of the user's shopping list
    """
    tax = 0   # To track the total amount of tax
    subtotal = 0  # To track the total price of all the items
    list_text = ""  # String list that will be printed to the console and returned
    list_text += "\nYour Shopping List - \n"
    list_text += ("{0:75s} {1:>20s} {2:>18s} {3:>20s}".format("\tName of the Item:", "Unit Price($):", "Quantity:", "Total Price($):") + "\n")
    for item in myList:
        list_text += ("\t" + str(item) + "\n")
        subtotal += item.getTotalPrice()
        if( item.IsTaxable()):
            tax = tax + (item.getPrice() * item.getTaxRate())
    total = subtotal + tax
    list_text += ("\n{0:>125s} {1:>8.2f}\n".format("Your subtotal is:", subtotal))
    list_text += ("{0:>125s} {1:>8.2f}\n".format("Tax:", tax))
    list_text += ("{0:>125s} {1:>8.2f}\n".format("Your total amount:", total))
    print(list_text)
    return list_text

def saveList(list_text):
    """
    Write the list to a text file with a datestamp using the string representation
    cite - https://www.saltycrane.com/blog/2008/06/how-to-get-current-date-and-time-in/

    :param list_text:
    :return:
    """
    today = datetime.datetime.now().strftime("%m%d%Y")  # Get the date for the date stamp
    filename = "shopping_list_" + today + ".txt"
    outfile = open(filename, "w")
    outfile.write(list_text)
    outfile.close()

def main():
    """
    The main function runs the ShoppingListGenerator. This method loads the pricing list into the program as a list
    of dictionaries. It then prompts the user to enter a search term for items the user wants. It produces a list of
    possible items that match the user's search parameters and prompts the user to keep returning if the list has more
    than one item. It prompts the user to search again or exit and prompts the user to save the file.
    """
    price_list = getShoppingPriceList()   # Get the pricing list
    myList = []                             # Create the user's list
    isShopping = True                       # Set isShopping to true to run the loop


    # Several items are pre-loaded into the list for testing
    """
    item1 = ShoppingItem("Apples", 2.99, "N", 2)
    item2 = ShoppingItem("Bacon", 2.99, "N", 3)
    item3 = ShoppingItem("Beef Stew Meat", 4.19, "N", 4)
    item4 = ShoppingItem("Butter", 2.29, "N", 5)
    item5 = ShoppingItem("Carrots", 1.29, "N", 4)
    item7 = ShoppingItem("Coffee", 4.99, "N", 2)
    item8 = ShoppingItem("Eggs", 1.09, "N", 1)
    item9 = ShoppingItem("French Fries", 1.89, "N", 1)
    item10 = ShoppingItem("Ground Beef (sirloin, 90/10)", 3.49, "N", 1)
    item11 = ShoppingItem("Ham", 2.99, "N", 2)
    item12 = ShoppingItem("Hand soap", 0.89, "Y", 4)
    item13 = ShoppingItem("Ketchup", 1.49, "N", 2)
    item14 = ShoppingItem("Measuring spoons", 1.19, "Y", 1)
    item15 = ShoppingItem("Milk", 1.99, "N", 2)
    item16 = ShoppingItem("Oat Meal", 1.49, "N", 2)
    item18 = ShoppingItem("Spatula", 1.79, "Y", 3)
    item20 = ShoppingItem("Wipes", 2.89, "Y", 2)
    
    myList.append(item2)
    myList.append(item3)
    myList.append(item4)
    myList.append(item5)
    myList.append(item7)
    myList.append(item8)
    myList.append(item9)
    myList.append(item10)
    myList.append(item11)
    myList.append(item12)
    myList.append(item13)
    myList.append(item14)
    myList.append(item15)
    myList.append(item16)
    myList.append(item18)
    myList.append(item20)
    """

    while( isShopping ):
        usingList = True  # Set to true if the user will be using the list

        # Prompt user to enter an item for the shopping list
        item = input("\nPlease enter an item to search the list. Enter 'exit' to exit: ")

        # If the user types exit, prompts the user to save and exits the program
        if( item == 'exit'):
            isShopping = False;
            if( len(myList) == 0):
                print("\n No items were chosen. Shopping list was not created.")
            else:
                list_text = displayList(myList)
                savedList = input("To save the list, enter 'Y' or 'y': ")
                if( savedList.upper() == 'Y'):
                    saveList(list_text)
            break

        # Gets the list of possible item matches for the user
        while( usingList):
            items = findItems(price_list, item)

            # If no items were found, prints message to the console and exits the inner loop
            if( len(items) == 0):
                print("\nSorry, the item entered wasn't found.")
                usingList = False

            # Prints the list of matches to the screen
            else:
                print("\nThe following items were found: \n")
                for i in range(len(items)):
                    print("\t{}. {} - {}".format(i+1, items[i]["Name"], items[i]["Price"]))

                # Ask the user to choose from the list, validate user input
                str_index = input("\nPlease select an item from the list or enter '0' to search again. ")
                isValid = False
                while( not isValid):  # index > len(items)
                    if( not str_index.isdigit()):
                        str_index = input("Entry is not an integer. Please select an item from the list or enter '0' to search again. ")
                    elif( int(str_index) not in range(0, len(items) + 1)):
                        str_index = input("Entry is not in the list. Please select an item from the list or enter '0' to search again. ")
                    else:
                        index = int(str_index)
                        isValid = True

                if( index != 0):

                    # Ask the user for a quantity
                    qty = eval(input("Please enter the quantity of the item you would like to purchase. "))

                    # Create the shopping item and add to the list
                    shopping_item = ShoppingItem(items[index - 1]["Name"], items[index - 1]["Price"],
                                                 items[index - 1]["Taxable"], qty)
                    myList.append(shopping_item)
                    print("\n{1} was added to your list, quantity of {0}.\n".format(shopping_item.getQuantity(), shopping_item.getName()))

                    # Prompts the user to return to the search list if the search list has more than one item
                    if( len(items) <= 1):
                        usingList = False
                    else:
                        key = input("Press any key to return to the search list or enter '0' to search again. ")
                        if(key == '0'):
                            usingList = False

                # Exit the inner loop
                else:
                    usingList = False


# Run main method
main()
