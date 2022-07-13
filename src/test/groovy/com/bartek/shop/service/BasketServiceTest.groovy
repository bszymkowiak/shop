package com.bartek.shop.service

import com.bartek.shop.model.dao.Basket
import com.bartek.shop.model.dao.Product
import com.bartek.shop.model.dao.User
import com.bartek.shop.repository.BasketRepository
import spock.lang.Specification

class BasketServiceTest extends Specification {

    def basketRepository = Mock(BasketRepository)
    def productService = Mock(ProductService)
    def userService = Mock(UserService)
    def basketService = new BasketService(basketRepository, productService, userService)

    def "FindById"() {

        given:
        def id = 1

        when:
        basketService.findById(id)

        then:
        1 * basketRepository.getById(id)
        0 * _
    }

    def "AddToBasket"() {
        given:
        def basket = Mock(Basket)
        def id = 1
        def product = Mock(Product)
        def user = Mock(User)

        when:
        basketService.addToBasket(basket, id)

        then:
        1 * productService.findById(id) >> product
        1 * basket.setProduct(product)
        1 * userService.getCurrentUser() >> user
        1 * basket.setUser(user)
        1 * basketRepository.save(basket)
        0 * _

    }

    def "UpdateProduct"() {
        given:
        def basket = Mock(Basket)
        def id = 1
        def basketDb = Mock(Basket)
        def quantity = 20

        when:
        basketService.updateProduct(basket, id)

        then:
        1 * basketRepository.getById(id) >> basketDb
        1 * basket.getQuantity() >> quantity
        1 * basketDb.setQuantity(quantity)
        0 * _
    }

    def "GetUserBasket"() {
        given:
        def set = [] as Set
        // ???????????
    }
}
