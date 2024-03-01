import './App.css'
import { useState } from 'react'
import Question from './components/Question'
import { questiondata } from './data'


function App() {
  return (
    <>
      {questiondata.map((questions) => {
      return(
        <Question
        key={questions.answer}
        question={questions.question}
        options={questions.options}
        answer={questions.answer}
        media={questions.media}/>
      );
    })}
    </>
  )
}

export default App
