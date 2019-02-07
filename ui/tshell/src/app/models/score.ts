import { TopicWiseScore } from './topic-wise-score';

export class Score {
    topicWiseScore: TopicWiseScore[] = [];

    constructor(data: any) {
        if (data) {
            // this.questions = [];
            for (const topicScore of data) {
                this.topicWiseScore.push(new TopicWiseScore(topicScore));
            }
        }
    }
}
