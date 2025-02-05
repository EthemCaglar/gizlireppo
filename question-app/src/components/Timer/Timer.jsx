import { useEffect, useState } from "react";

const Timer = ({ seconds }) => {
  const [timeLeft, setTimeLeft] = useState(seconds);

  useEffect(() => {
    if (!timeLeft) return;

    const intervalId = setInterval(() => {
      setTimeLeft(timeLeft - 1);
    }, 1000);


    return () => clearInterval(intervalId);}, [timeLeft]);

  return (
    <div>
      <h1>{timeLeft}</h1>
    </div>
  );
};

export default Timer;