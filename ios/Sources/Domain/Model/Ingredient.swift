//
//  Ingredient.swift
//  MixParadise
//
//  Created by Alexandre Frigon on 2019-03-23.
//  Copyright © 2019 Mirego. All rights reserved.
//

import Foundation

class Ingredient: Decodable {
    var id: String = ""
    var label: String = ""
    var type: String = ""
    var imageUrl: String = ""
}
