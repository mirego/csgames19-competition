import Foundation

class Solid: Ingredient {
    let weight: Double
    let sprites: [String]?

    private enum CodingKeys: String, CodingKey {
        case weight
        case sprites
    }

    required init(from decoder: Decoder) throws {
        let container = try decoder.container(keyedBy: CodingKeys.self)
        self.weight = try container.decode(Double.self, forKey: .weight)
        self.sprites = try? container.decode([String].self, forKey: .sprites)
        try super.init(from: decoder)
    }

    func randomSpriteUrl() -> String? {
        guard let sprites = sprites else { return nil }
        return sprites[Int.random(in: 0..<sprites.count)]
    }
}
