import Timer from "../Timer/Timer";
import { useEffect, useState } from "react";


const Question = (props) => {
    let answerArray = []
    return(
        <div className="table">
            <div className="upper">
                <img className="image" src={props.media} height={400} alt={props.answer} />
                <h1 className="question" >{props.question}</h1>
            </div>
        <div className="options">
            {props.options.map((option) => {
                    return(
                        <button onClick={() => answerArray.push(option)}>{option}</button>
                    );
                })}
                
        </div>
        </div>
    );
};



export default Question;