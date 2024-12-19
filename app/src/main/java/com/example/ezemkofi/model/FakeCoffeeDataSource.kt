package com.example.ezemkofi.model

import com.example.ezemkofi.R

object FakeCoffeeDataSource {
    val americanoCategory = CoffeeCategory(4, "Americano")
    val cappuccinoCategory = CoffeeCategory(2, "Cappuccino")
    val espressoCategory = CoffeeCategory(1, "Espresso")
    val latteCategory = CoffeeCategory(3, "Latte")
    val macchiatoCategory = CoffeeCategory(6, "Macchiato")
    val mochaCategory = CoffeeCategory(5, "Mocha")

    val coffeeList = listOf(
        Coffee(
            1, "Espresso Elegance", espressoCategory, 4.5, 2.99,
            "A smooth and rich espresso with a delicate crema, perfect for purists.",
            R.drawable.espresso_elegance
        ),
        Coffee(
            2, "Cappuccino Bliss", cappuccinoCategory, 4.4, 4.25,
            "A harmonious blend of espresso, steamed milk, and a generous frothy top.",
            R.drawable.cappuccino_bliss
        ),
        Coffee(
            3, "Luscious Latte", latteCategory, 4.3, 3.75,
            "A velvety latte with perfectly balanced espresso and milk, ideal for any time of day.",
            R.drawable.luscious_latte
        ),
        Coffee(
            4, "Americano Awakening", americanoCategory, 4.1, 3.45,
            "Bold espresso diluted with hot water for a smoother yet intense flavor.",
            R.drawable.americano_awakening
        ),
        Coffee(
            5, "Mocha Marvel", mochaCategory, 4.6, 4.49,
            "A chocolate lover's dream, combining rich espresso with creamy chocolate and steamed milk.",
            R.drawable.mocha_marvel
        ),
        Coffee(
            6, "Macchiato Moments", macchiatoCategory, 4.0, 3.55,
            "A classic macchiato with a shot of espresso marked by a dollop of frothy milk.",
            R.drawable.macchiato_moments
        ),
        Coffee(
            7, "Espresso Royale", espressoCategory, 4.4, 3.25,
            "A premium espresso with a deep, robust flavor, fit for royalty.",
            R.drawable.espresso_royale
        ),
        Coffee(
            8, "Cappuccino Dream", cappuccinoCategory, 4.7, 4.75,
            "A dreamy cappuccino with thick foam and a hint of cocoa for a luxurious experience.",
            R.drawable.cappuccino_dream
        ),
        Coffee(
            9, "Latte Luxe", latteCategory, 4.6, 4.15,
            "An indulgent latte with creamy milk and a strong espresso base, offering a luxurious taste.",
            R.drawable.latte_luxe
        ),
        Coffee(
            10, "Classic Americano", americanoCategory, 4.2, 3.29,
            "A simple yet satisfying Americano with a smooth and mellow finish.",
            R.drawable.classic_americano
        ),
        Coffee(
            11, "Divine Mocha", mochaCategory, 4.8, 4.79,
            "A heavenly combination of rich chocolate, espresso, and steamed milk.",
            R.drawable.divine_mocha
        ),
        Coffee(
            12, "Macchiato Magic", macchiatoCategory, 4.3, 3.65,
            "An enchanting macchiato with a perfect balance of espresso and milk.",
            R.drawable.macchiato_magic
        ),
        Coffee(
            13, "Espresso Extravaganza", espressoCategory, 4.6, 3.49,
            "A bold and extravagant espresso with a full-bodied flavor for true coffee lovers.",
            R.drawable.espresso_extravaganza
        ),
        Coffee(
            14, "Cappuccino Carnival", cappuccinoCategory, 4.5, 4.99,
            "A festive cappuccino with frothy milk and a sprinkle of cinnamon for a touch of fun.",
            R.drawable.cappuccino_carnival
        ),
        Coffee(
            15, "Latte Love Affair", latteCategory, 4.7, 4.35,
            "Fall in love with this creamy latte, where espresso meets perfectly steamed milk.",
            R.drawable.latte_love_affair
        ),
        Coffee(
            16, "American Dream", americanoCategory, 4.3, 3.39,
            "A rich and smooth Americano that’s as strong as the dream it’s named after.",
            R.drawable.american_dream
        ),
        Coffee(
            17, "Mocha Madness", mochaCategory, 4.9, 4.89,
            "A decadent mocha with intense chocolate flavor and robust espresso, perfect for indulging.",
            R.drawable.mocha_madness
        ),
        Coffee(
            18, "Magical Macchiato", macchiatoCategory, 4.2, 3.79,
            "A magical blend of espresso and milk, creating a flavorful macchiato experience.",
            R.drawable.magical_macchiato
        ),
        Coffee(
            19, "Espresso Supreme", espressoCategory, 4.5, 3.79,
            "A supreme espresso that delivers a rich, intense shot with a velvety finish.",
            R.drawable.espresso_supreme
        ),
        Coffee(
            20, "Cappuccino Delight", cappuccinoCategory, 4.4, 4.69,
            "A delightful cappuccino with smooth foam and perfectly balanced espresso.",
            R.drawable.cappuccino_delight
        ),
        Coffee(
            21, "Latte Indulgence", latteCategory, 4.6, 4.25,
            "An indulgent latte that’s creamy and rich, perfect for those who love their coffee smooth.",
            R.drawable.latte_indulgence
        ),
        Coffee(
            22, "American Classic", americanoCategory, 4.2, 3.49,
            "A timeless Americano that’s simple yet satisfying, perfect for a coffee purist.",
            R.drawable.american_classic
        ),
        Coffee(
            23, "Mocha Mania", mochaCategory, 4.7, 4.99,
            "A mocha that offers a bold mix of chocolate and espresso, creating a coffee experience like no other.",
            R.drawable.mocha_mania
        ),
        Coffee(
            24, "Macchiato Marvel", macchiatoCategory, 4.3, 3.95,
            "A marvelous macchiato that’s perfectly balanced with rich espresso and frothy milk.",
            R.drawable.macchiato_marvel
        ),
        Coffee(
            25, "Classic Black Coffee", americanoCategory, 4.0, 2.99,
            "A no-nonsense classic black coffee with deep flavor and a smooth finish.",
            R.drawable.classic_black_coffee
        ),
        Coffee(
            26, "Double Chocolate Delight", mochaCategory, 4.8, 5.29,
            "A mocha with double the chocolate for an irresistible and indulgent treat.",
            R.drawable.double_chocolate_delight
        ),
        Coffee(
            27, "Macchiato Symphony", macchiatoCategory, 4.4, 3.89,
            "A symphony of flavors, combining espresso with just the right amount of frothy milk.",
            R.drawable.macchiato_symphony
        )
    )
}