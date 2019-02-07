import { Topic } from './topic';

export class Skill {
    id: number;
    name: string;
    searchCount: number;
    active: string;
    testCount: number;
    description: string;
    creationDate: Date;
    topics: Topic[];

    constructor(name, active, description, topics, creationDate) {
        this.name = name;
        this.active = active;
        this.description = description;
        this.topics = topics;
        this.creationDate = creationDate;
    }
}

export class ReferenceSkill {
    id: number;
    skill: Skill;
    referenceSkill: Skill;
    classifier: string;
    constructor(id, skill, referenceSkill, classifier) {
        this.id = id;
        this.skill = skill;
        this.referenceSkill = referenceSkill;
        this.classifier = classifier;
    }
}
