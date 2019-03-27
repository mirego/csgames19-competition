import Foundation

class Ingredient: Codable {
    enum IngredientType: String, Codable {
        case liquid = "liquid"
        case solid = "solid"
    }

    let id: String
    let label: String
    let type: IngredientType
    let imageUrl: String
}
