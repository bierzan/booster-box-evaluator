package com.brzn.bboxeval.box.domain

import spock.lang.Specification

class BoxServiceTest extends Specification implements SampleBoxes {
    def repository = Mock(BoxRepository);
    def service = new BoxService(repository);

    def "SearchNew"() {
        given: 'Inventory with oldBox'
        repository.findLast() >> oldBox;
        when: 'I look for new boxes'
        def newBoxes = service.searchNew()
        then: 'I get boxes released after oldBox'
        newBoxes.each {assert it.releaseDate > oldBox.releaseDate}
    }
}
//todo czy potrzebuje calego boxa czy tylko daty?