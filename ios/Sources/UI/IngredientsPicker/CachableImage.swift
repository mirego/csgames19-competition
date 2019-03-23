//
//  CachableImage.swift
//  MixParadise
//
//  Created by Morteza Ahmadi on 2019-03-23.
//  Copyright © 2019 Mirego. All rights reserved.
//
import UIKit

class CachableImage : UIImageView {
    
    override func awakeFromNib() {
        self.backgroundColor = .clear
        self.clipsToBounds = true
        self.layer.masksToBounds = true
        self.contentMode = .scaleAspectFill
    }
    
    //Cache images in order to prevent excessive data usage.
    let imageCache = NSCache<AnyObject, AnyObject>()
    
    /**
     Download image and cache them in order to prevent excessive data usage.
     
     - Parameters:
     - withURLString: the image URL
     
     - Returns:
     */
    
    func downloadImage(from URLString: String) {
        //we are reusing cells. Sometimes we need to reset cell components to show the right image.
        image = nil
        
        //Caching
        if let imageFromCache = imageCache.object(forKey: URLString as AnyObject) as? UIImage {
            self.image = imageFromCache
            return
        }
        
        //Download the product image
        guard let url = URL(string: URLString) else { return }
        URLSession.shared.dataTask(with: url) { (data, response, error) in
            guard error == nil else { return } // A terrible way to handle error!
            guard let data = data else { return }
            DispatchQueue.main.async {
                let imageToCache = UIImage(data: data)
                self.imageCache.setObject(imageToCache!, forKey: URLString as AnyObject)
                self.image = imageToCache
            }
            }.resume()
    }
}
