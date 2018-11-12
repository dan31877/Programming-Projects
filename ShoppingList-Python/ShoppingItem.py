'''
Daniel Anderson
CS521  Due 5/2/2018
Project
ShoppingItem.py
'''

class ShoppingItem:
    """ Class ShoppingItem represent a Shopping item"""

    def __init__(self, name, price, isTaxable, quantity=1):
        """
            Constructor for a ShoppingItem object
            Arguments:
                name - name of the item, str
                price - price of the item, float
                isTaxable - determines if the item is taxable, str converts to boolean
                quantity - quantity of this shopping item, int (default = 0)
        """
        self.__name = name
        self.__price = price
        self.__quantity = quantity
        self.__taxRate = 0.05   # Tax rate set at 5%
        if(isTaxable == "Y"):   # Convert to boolean
            self.__isTaxable = True
        else:
            self.__isTaxable = False


    def getName(self):
        """ Return the name of the shopping item """
        return self.__name

    def getPrice(self):
        """ Return the price of the shopping item """
        return self.__price

    def getQuantity(self):
        """ Return the quantity of the shopping item """
        return self.__quantity

    def IsTaxable(self):
        """ Returns true or false based on whether the shopping item is taxable"""
        return self.__isTaxable

    def getTaxRate(self):
        """ Return the tax rate of the shopping item """
        return self.__taxRate

    def getTotalPrice(self):
        """ Return the quantity times the price of the shopping item(s) """
        return (self.__quantity * self.__price)

    def getTotalPriceWithTax(self):
        """ Return the quantity times the price, plus tax if applicable, of the shopping item(s) """
        if( self.__isTaxable):
            return (self.__quantity * (self.__price + (self.__price * self.__taxRate)))
        else:
            return (self.__quantity * self.__price)

    def __str__(self):
        """
        Returns a string representation of the shopping item. Formatted to match the output from the list
        """
        return ("{0:75s} {1:18.2f} {2:18d} {3:18.2f}".format(self.getName(), self.getPrice(), self.getQuantity(), self.getTotalPrice()))


