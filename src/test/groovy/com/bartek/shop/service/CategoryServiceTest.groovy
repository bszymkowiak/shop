package com.bartek.shop.service

import com.bartek.shop.model.dao.Category
import com.bartek.shop.repository.CategoryRepository
import org.springframework.data.domain.PageRequest
import spock.lang.Specification

class CategoryServiceTest extends Specification {

    def categoryRepository = Mock(CategoryRepository)
    def categoryService = new CategoryService(categoryRepository)

    def "Save"() {
        given:
        def category = Mock(Category)

        when:
        categoryService.save(category)

        then:
        1 * categoryRepository.save(category)
        0 * _
    }

    def "GetCategoryById"() {
        given:
        def id = 1

        when:
        categoryService.getCategoryById(id)

        then:
        1 * categoryRepository.findById(id)
        0 * _
    }

    def "DeleteCategoryById"() {
        given:
        def id = 1

        when:
        categoryService.deleteCategoryById(id)

        then:
        1 * categoryRepository.deleteById(id)
        0 * _
    }

    def "GetPage"() {
        given:
        def pageable = PageRequest.of(1, 5)

        when:
        categoryService.getPage(pageable)

        then:
        1 * categoryRepository.findAll(pageable)
        0 * _
    }

    def "UpdateCategory"() {
    }
}
