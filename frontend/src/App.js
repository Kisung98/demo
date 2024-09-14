import axios from "axios";
import React, { useState } from "react";
import { useEffect } from "react";

const App = () => {
  const [data, setData] = useState([]);
  useEffect(() => {
    axios
      .post("http://122.46.14.246:8080/getData", {
        param1: undefined,
        param2: null,
        param3: "파라미터",
      })
      .then((res) => {
        console.log(res);
        setData(res.data);
      });
  }, []);
  return (
    <div>
      <div>배포확인</div>
      {data.map((value, index) => {
        return (
          <div>
            <div>{index + 1}번째</div>
            <div>{value.id}</div>
            <div>{value.content}</div>
            <hr />
          </div>
        );
      })}
    </div>
  );
};

export default App;
