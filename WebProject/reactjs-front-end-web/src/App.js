import React from "react";
import { BrowserRouter as Router, Routes, Route, Navigate } from "react-router-dom";
import "bootstrap/dist/css/bootstrap.min.css";
import "./App.css";

import Login from "./Components/Users/Login";
import Home from "./Components/Users/Home-me/Home";
import CheckBlacklist from "./Components/Users/CheckBlacklist";
import CheckWhitelist from "./Components/Users/CheckWhitelist";
import Footer from "./Components/Users/Footer-me/Footer";
import NavBar from "./Components/Users/Navbar-me/Navbar";
import Report from "./Components/Users/Report";

const isAuthenticated = () => {
  const userToken = localStorage.getItem("userToken");
  return userToken ? true : false;
};

const PrivateRoute = ({ element, path }) => {
  return isAuthenticated() ? (
    element
  ) : (
    <Navigate to="/" state={{ from: path }} replace />
  );
};

const App = () => {
  return (
    <div className="p-1">
      <Router>
        <div className="container mt-3">
          <Routes>
            <Route
              path="/login"
              element={<Login />} />
            <Route
              path="/home"
              element={
                <React.Fragment>
                  <NavBar />
                  <PrivateRoute element={<Home />} path="/home" />
                  <Footer />
                </React.Fragment>
              }
            />
              <Route
              path="/checkBlacklist"
              element={
                <React.Fragment>
                  <NavBar />
                  <PrivateRoute element={<CheckBlacklist />} path="/checkBlacklist" />
                  <Footer />
                </React.Fragment>
              }
            />
            <Route
              path="/checkWhitelist"
              element={
                <React.Fragment>
                  <NavBar />
                  <PrivateRoute element={<CheckWhitelist />} path="/checkWhitelist" />
                  <Footer />
                </React.Fragment>
              }
            />
            <Route
              path="/report"
              element={
                <React.Fragment>
                  <NavBar />
                  <PrivateRoute element={<Report />} path="/report" />
                  <Footer />
                </React.Fragment>
              }
            />
          </Routes>
        </div>
      </Router>
    </div>
  );
};

export default App;
