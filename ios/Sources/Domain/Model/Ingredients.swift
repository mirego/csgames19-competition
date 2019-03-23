//
//  Ingredients.swift
//  MixParadise
//
//  Created by Alexandre Frigon on 2019-03-23.
//  Copyright © 2019 Mirego. All rights reserved.
//

class Ingredients: Decodable {
    let juices: [Liquid]
    let drinks: [Liquid]
    let ingredients: [Solid]
    let alcohols: [Liquid]
}
