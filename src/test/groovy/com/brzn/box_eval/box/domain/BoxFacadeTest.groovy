package com.brzn.box_eval.box.domain


import spock.lang.Specification

class BoxFacadeTest extends Specification implements SampleBoxes {

    private BoxFacade facade

    // todo wydzielic do ustawiacza testow
    def setup() {
        facade = createFacade();
    }

    def "adding single box"() {

        when: "I add box to repository"
        def addedBox = facade.add(sampleBox);

        then: "I see that it returned dto with same data + id"
        addedBox.id != 0
        addedBox.id != sampleBox.id


    }
    def createFacade () {
        InMemoryBoxRepository repository = new InMemoryBoxRepository();
        BoxCommand command = new BoxCommand(new BoxCreator(), repository);
        return new BoxFacade(command, repository);
    }

}
