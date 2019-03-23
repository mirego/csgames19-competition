//
//  APIData.swift
//  MixParadise
//
//  Created by Nathan Reed on 3/23/19.
//  Copyright © 2019 Mirego. All rights reserved.
//

import Foundation


struct APIData: Codable {
    var juices: [Liquid]
    var drinks: [Liquid]
    var alcohols: [Liquid]
    var ingredients: [Solid]
}

struct Liquid: Codable {
    var id: String
    var label: String
    var type: String
    var color: String
    var opacity: Double
    var imageUrl: String
}

struct Solid: Codable {
    var id: String
    var label: String
    var type: String
    var weight: Double
    var imageUrl: String
}
