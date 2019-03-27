import Foundation

class Liquid: Ingredient {
    let color: String
    let opacity: Double

    private enum CodingKeys: String, CodingKey {
        case color
        case opacity
    }

    required init(from decoder: Decoder) throws {
        let container = try decoder.container(keyedBy: CodingKeys.self)
        self.color = try container.decode(String.self, forKey: .color)
        self.opacity = try container.decode(Double.self, forKey: .opacity)
        try super.init(from: decoder)
    }
}
