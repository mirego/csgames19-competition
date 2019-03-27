import UIKit

struct BlenderIngredientViewModel {
    enum Content {
        case solid(UIImage?)
        case liquid(UIColor)
    }

    let content: Content

    init(image: UIImage?) {
        content = .solid(image)
    }

    init(liquid: Liquid) {
        content = .liquid(UIColor(hexString: liquid.color) ?? .red)
    }
}
