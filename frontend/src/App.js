import React, { useState, useEffect } from "react";

const App = () => {
  const [input, setInput] = useState("");
  const [socket, setSocket] = useState(null);

  useEffect(() => {
    const ws = new WebSocket("ws://localhost:8080/ws");

    ws.onopen = () => {
      console.log("WebSocket 연결 성공");
    };

    ws.onmessage = (message) => {
      console.log("서버로부터 받은 메시지:", message.data);
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
      <div>소켓 확인</div>
      <input
        type="text"
        value={input}
        onChange={(e) => {
          setInput(e.target.value);
        }}
      />
      <button
        onClick={() => {
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
