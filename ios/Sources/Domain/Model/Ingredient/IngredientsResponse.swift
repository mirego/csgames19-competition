import Foundation

struct IngredientsResponse: Codable {
    let juices: [Liquid]?
    let drinks: [Liquid]?
    let ingredients: [Solid]?
    let alcohols: [Liquid]?
}
