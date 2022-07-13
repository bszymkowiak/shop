package com.bartek.shop.service

import com.bartek.shop.config.properties.FilePathConfig
import com.bartek.shop.helper.FileHelper
import com.bartek.shop.model.dao.Product
import com.bartek.shop.repository.ProductRepository
import org.apache.commons.io.FilenameUtils
import org.springframework.data.domain.PageRequest
import org.springframework.web.multipart.MultipartFile
import spock.lang.Specification

import java.nio.file.Path
import java.nio.file.Paths

class ProductServiceTest extends Specification {

    def productRepository = Mock(ProductRepository)
    def filePathConfig = Mock(FilePathConfig)
    def fileHelper = Mock(FileHelper)
    def productService = new ProductService(productRepository, filePathConfig, fileHelper)

    def "Save product with image"() {
        given:
        def product = Mock(Product)
        def image = Mock(MultipartFile)
        def inputStream = Mock(InputStream)
        def path = Paths.get("filePath", "productName.extension")

        when:
        productService.save(product, image)

        then:
        1 * filePathConfig.getProduct() >> "filePath"
        1 * product.getName() >> "productName"
        1 * image.getOriginalFilename() >> "image.extension"
        1 * image.getInputStream() >> inputStream
        1 * fileHelper.saveFile(inputStream, path)
        1 * product.setFilePath(path.toString())
        1 * productRepository.save(product)
        0 * _
    }

    def "save product without image"() {

    }

    def "FindById"() {
        given:
        def id = 1

        when:
        productService.findById(id)

        then:
        1 * productRepository.getById(id)
        0 * _
    }

    def "DeleteProductById"() {
        given:
        def id = 1
        def productDb = Mock(Product)
        def path = Paths.get("pathfile.jpg")

        when:
        productService.deleteProductById(id)

        then:
        1 * productRepository.getById(id) >> productDb
        1 * productDb.getFilePath() >> "pathfile.jpg"
        1 * fileHelper.deleteFile(path)
        1 * productRepository.deleteById(id)
        0 * _
    }

    def "Delete product when fileHelper throws exception"(){

        given:
        def id = 1
        def productDb = Mock(Product)
        def path = Paths.get("pathfile.jpg")

        when:
        productService.deleteProductById(id)

        then:
        1 * productRepository.getById(id) >> productDb
        1 * productDb.getFilePath() >> "pathfile.jpg"
        1 * fileHelper.deleteFile(path) >> {throw new IOException()}
        1 * productRepository.deleteById(id)
        0 * _

    }


    def "GetPage"() {

        given:
        def pageable = PageRequest.of(1, 5)

        when:
        productService.getPage(pageable)

        then:
        1 * productRepository.findAll(pageable)
        0 * _
    }

    def "UpdateProduct"() {
        given:
        def id = 1
        def product = Mock(Product)
        def image = Mock(MultipartFile)
        def productDb = Mock(Product)
        def productDbFilePath = "productDbFilePath.jpg"
        def path = Paths.get("filePath", "productName.extension")
        def inputStream = Mock(InputStream)
        def filePath = Paths.get("productDbFilePath.jpg")

        when:
        productService.updateProduct(id, product, image)

        then:
        1 * productRepository.getById(id) >> productDb
        1 * productDb.getFilePath() >> productDbFilePath
        1 * product.getPrice() >> 20.0
        1 * productDb.setPrice(20.0)
        1 * product.getName() >> "name"
        1 * productDb.setName("name")
        1 * product.getQuantity() >> 5
        1 * productDb.setQuantity(5)
        1 * image.isEmpty() >> false
        1 * filePathConfig.getProduct() >> "filePath"
        1 * productDb.getName() >> "productName"
        1 * image.getOriginalFilename() >> "productName.extension"
        1 * image.getInputStream() >> inputStream
        1 * fileHelper.saveFile(inputStream, path)
        1 * product.setFilePath(path.toString())
        1 * productDb.getFilePath() >> "filePath.jpg"
        1 * fileHelper.deleteFile(filePath)
        0 * _

    }
}
