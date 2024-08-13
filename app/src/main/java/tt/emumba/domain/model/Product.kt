package tt.emumba.domain.model

data class Product(
    val id: Int,
    val title: String,
    val price: Double,
    val description: String,
    val image: String,
    val category: Category
)


//"id": 18,
//"title": "Sleek White & Orange Wireless Gaming Controller",
//"price": 69,
//"description": "Elevate your gaming experience with this state-of-the-art wireless controller, featuring a crisp white base with vibrant orange accents. Designed for precision play, the ergonomic shape and responsive buttons provide maximum comfort and control for endless hours of gameplay. Compatible with multiple gaming platforms, this controller is a must-have for any serious gamer looking to enhance their setup.",
//"images": [
//"https://i.imgur.com/ZANVnHE.jpeg",
//"https://i.imgur.com/Ro5z6Tn.jpeg",
//"https://i.imgur.com/woA93Li.jpeg"
//],
//"creationAt": "2024-08-12T23:30:58.000Z",
//"updatedAt": "2024-08-12T23:30:58.000Z",
//"category": {
//    "id": 2,
//    "name": "Electronics",
//    "image": "https://i.imgur.com/ZANVnHE.jpeg",
//    "creationAt": "2024-08-12T23:30:58.000Z",
//    "updatedAt": "2024-08-12T23:30:58.000Z"
//}