export class TopicWiseScore {
    score: number;
    topicName: string;
    percentage: number;
    totalScore: number;
    outof: number;

    constructor(data: any) {
        data = data || {};
        this.score = data.score;
        this.topicName = data.topicName;
        this.percentage = data.percentage;
        this.totalScore = data.totalScore;

    }
}
