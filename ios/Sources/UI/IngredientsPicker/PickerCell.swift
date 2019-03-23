//
//  pickerCell.swift
//  MixParadise
//
//  Created by Morteza Ahmadi on 2019-03-23.
//  Copyright © 2019 Mirego. All rights reserved.
//

import UIKit

class PickerCell: UICollectionViewCell {
    
    override init(frame: CGRect) {
        super.init(frame: frame)
        addSubview(image)
        //addSubview(label)
    }
    
    override func layoutSubviews() {
        image.pin.all()
        //label.pin.centerEnd()
    }
    
    required init?(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    var image: CachableImage = CachableImage()
    //var label = UILabel()
    
    func configureCell(from model: Juice?) {
        self.image.downloadImage(from: model?.imageUrl ?? "")
//        self.label.text = model?.type
    }
    
    func confid(from model: Drink?) {
        self.image.downloadImage(from: model?.imageUrl ?? "")
    }
}
