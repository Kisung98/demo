import React, { useState, useEffect, useRef } from "react";

const App = () => {
  const [input, setInput] = useState("");
  const [socket, setSocket] = useState(null);
  const [list, setList] = useState([]);
  const buttonRef = useRef(null);

  useEffect(() => {
    const ws = new WebSocket("ws://localhost:8001/ws");

    ws.onopen = () => {
      console.log("WebSocket 연결 성공");
    };

    ws.onmessage = (message) => {
      console.log(message);
      setList((prev) => {
        return [...prev, message.data];
      });
    };

    ws.onclose = () => {
      console.log("WebSocket 연결이 종료되었습니다.");
    };

    setSocket(ws);

    return () => {
      ws.close();
    };
  }, []);

  return (
    <div>
      {list.map((item, index) => {
        return <div key={index}>{item}</div>;
      })}
      <input
        type="text"
        value={input}
        onChange={(e) => {
          setInput(e.target.value);
        }}
        onKeyDown={(e) => {
          if (e.key == "Enter") {
            buttonRef.current.click();
          }
        }}
      />
      <button
        ref={buttonRef}
        onClick={(e) => {
          console.log(list);
          socket.send(input);
          setInput("");
        }}
      >
        전송
      </button>
    </div>
  );
};

export default App;
