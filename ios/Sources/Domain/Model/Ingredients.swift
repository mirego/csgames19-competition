//
//  Ingredients.swift
//  MixParadise
//
//  Created by Alexandre Frigon on 2019-03-23.
//  Copyright © 2019 Mirego. All rights reserved.
//

class Ingredients: Decodable {
    var juices: [Liquid] = []
    var drinks: [Liquid] = []
    var ingredients: [Solid] = []
    var alcohols: [Liquid] = []
}
