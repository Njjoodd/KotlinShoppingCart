package shoppingCart

/**
 * Returns a map of productId -> MutableMap("name" to String, "price" to Double, "stock" to Int).
 * Your task: Add exactly 5 items with correct details (IDs 1..5).
 * Example:
 *   1 -> { "name"="Laptop", "price"=350.000, "stock"=10 },
 *   2 -> { "name"="Smart TV", "price"=200.000, "stock"=5 },
 *   3 -> { "name"="Headphones", "price"=50.000, "stock"=20 },
 *   4 -> { "name"="Gaming Console", "price"=150.000, "stock"=8 },
 *   5 -> { "name"="Wireless Mouse", "price"=8.500, "stock"=15 },
 */
fun createStoreInventory(): MutableMap<Int, MutableMap<String, Any>> {
    //TODO("Implement createStoreInventory()")
    val inventory = mutableMapOf <Int, MutableMap<String, Any>> ()
    inventory [1] = mutableMapOf("name" to "Laptop", "price" to 350.000, "stock" to 10)
    inventory [2] = mutableMapOf("name" to "Smart TV", "price" to 200.000, "stock" to 5)
    inventory [3] = mutableMapOf("name" to "Headphones", "price" to 50.000, "stock" to 20)
    inventory [4] = mutableMapOf("name" to "Gaming Console", "price" to 150.000, "stock" to 8)
    inventory [5] = mutableMapOf("name" to "Wireless Mouse", "price" to 8.500, "stock" to 15)
return inventory
}

/**
 * Tries to add [quantity] of [productId] to [cart], if there's enough stock in [storeInventory].
 * - If valid, reduce the stock and increase the cart quantity for this product.
 * - Return true if successful, false otherwise.
 */
fun addToCart(
    storeInventory: MutableMap<Int, MutableMap<String, Any>>,
    cart: MutableMap<Int, Int>,
    productId: Int,
    quantity: Int
): Boolean {
    //TODO("Implement addToCart()")
    val product = storeInventory [productId] ?: return false
    val currentStock = product["stock"] as? Int?: return false

    if (currentStock < quantity )
    {return false}

    product ["stock"] = currentStock - quantity

    cart[productId] = (cart[productId] ?: 0) + quantity
    return true }

/**
 * Removes [quantity] of [productId] from [cart], restoring the same amount to [storeInventory].
 * - If cart has enough of this product, remove it and restore stock.
 * - Return true if successful, false otherwise.
 */
fun removeFromCart(
    storeInventory: MutableMap<Int, MutableMap<String, Any>>,
    cart: MutableMap<Int, Int>,
    productId: Int,
    quantity: Int
): Boolean {
    //TODO("Implement removeFromCart()")
    val product = storeInventory [productId] ?: return false
    val currentCartQuantity = cart [productId] ?: return false
    val currentStock = product["stock"] as? Int?: return false

   if (currentCartQuantity < quantity) {return false}
    product ["stock"] = currentStock + quantity
    cart[productId] = (cart[productId] ?: 0)  - quantity
    val newCartQuantity = currentCartQuantity - quantity
    if (newCartQuantity > 0) {
        cart[productId] = newCartQuantity
    } else {
        cart.remove(productId)
    }
    return true

}

/**
 * Calculates the total cost of items in [cart].
 * - For each (productId -> quantity), retrieve the price from [storeInventory].
 * - Multiply (price * quantity) and sum for the final total.
 */
fun calculateTotal(
    storeInventory: MutableMap<Int, MutableMap<String, Any>>,
    cart: MutableMap<Int, Int>
): Double {
    //TODO("Implement calculateTotal()")
    var total = 0.0
    var priceActual: Double = 0.0
    for ((productId, quantity) in cart) {
        val product = storeInventory[productId] ?: continue
        println("product list after first line: ${product}")
        var price = product["price"] as? Double ?: continue
        priceActual += price;
        total += price * quantity


    }
    return total;
}


    /**
     * Returns a list of product IDs whose "name" contains [keyword].
     * - For example, if keyword = "lap", then "Laptop" (ID 1) should match.
     * - Matching is case-insensitive.
     */
    fun filterProductsByName(
        storeInventory: MutableMap<Int, MutableMap<String, Any>>,
        keyword: String
    ): List<Int> {
        //TODO("Implement filterProductsByName()")
        return storeInventory.filter { productEntry ->
            productEntry.value["name"]?.toString()?.contains(keyword, ignoreCase = true) == true
        }
            .map { productEntry -> productEntry.key }
    }

    /**
     * Entry point for the project.
     * Run the JUnit tests to see if you've implemented each function correctly.
     */
    fun main() {
        createStoreInventory()
        val cart = mutableMapOf<Int, Int>()
        addToCart(createStoreInventory(), cart, 1, 2)
        removeFromCart(createStoreInventory(), cart, 3, 4)
        calculateTotal(createStoreInventory(), cart)
        val storeInventory = createStoreInventory()
        val filteredProducts = filterProductsByName(storeInventory, "lap")
        println("Welcome to the Kotlin Store project!")
        println("All functions currently throw NotImplementedError.")
        println("Open each function and replace TODO(...) with your implementation.")
    }
