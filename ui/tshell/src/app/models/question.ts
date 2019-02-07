import { Option } from './option';
import { QuestionDifficultyLevel } from './question-difficulty-level';
import { QuestionAnswerType } from './question-answer-type';
import { Topic } from './topic';
export class Question {
    // id: number;
    // question: string;
    // status: string;
    // level: QuestionDifficultyLevel;
    // answerType: QuestionAnswerType;
    // optionList: Option[];
    // topicList: Topic[];
    // answered: boolean;

    // constructor(data: any) {
    //     data = data || {};
    //     this.id = data.id;
    //     this.question = data.question;
    //     this.status = data.status;
    //     this.level = new QuestionDifficultyLevel(data.questionDifficultyLevel);
    //     this.answerType = new QuestionAnswerType(data.questionAnswerType);
    //     this.optionList = [];
    //     data.optionList.forEach(o => {
    //         this.optionList.push(new Option(o, this.answerType.id));
    //     });
    //     this.topicList = [];
    //     data.topicList.forEach(t => {
    //         this.topicList.push(new Topic(t));
    //     });
    // }
}
