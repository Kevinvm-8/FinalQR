import React from "react";
import { Container, Row, Col } from "react-bootstrap";
import myImg from "./Cat2.png";
import Tilt from "react-parallax-tilt";
import {
  AiFillGithub,
  AiOutlineTwitter,
  AiFillInstagram,
  AiOutlineMail,
} from "react-icons/ai";
import { FaLinkedinIn } from "react-icons/fa";

function Home2() {
  return (
    <Container fluid className="home-about-section" id="about">
      <Container>
        <Row>
          <Col md={8} className="home-about-description">
            <h1 style={{ fontSize: "2.6em" }} data-aos="fade-right">
              <span className="primary-header"> ABOUT </span> QRCode
            </h1>
            <p className="home-about-body" data-aos="fade-up">
              <br />
               <i className="primary-header">
               Utilizing tools to promptly detect malicious QR codes is crucial for assisting users in identifying them.
              </i>{" "}
              <br />
              <br />
              This proactive approach helps prevent unfortunate incidents that may arise when users accidentally access malicious links.
              <br />
              <br />{" "}
              <i className="primary-header"></i> The increased awareness among users, facilitated by such tools, contributes to a reduction in the number of victims of Quishing attacks.
              <br />
              <br />The timely identification provided by these tools ensures that users do not feel insecure or anxious whenever they engage in tasks involving QR codes.
              <i>
                <i className="primary-header"></i>
              </i>
              <br />
              <br />The malicious QR code identification tool aids users in recognizing and avoiding the scanning of harmful QR codes.{" "}
              <i className="primary-header"></i> 
              <i className="primary-header"></i>
              <br />
              <br />
              Thereby safeguarding their personal information and assets from potential online attacks and fraud.
              <i className="primary-header"></i>
            </p>
          </Col>
          <Col md={4} className="myAvtar">
            <Tilt>
              <img
                data-aos="fade-left"
                src={myImg}
                className="img-fluid"
                alt="avatar"
              />
            </Tilt>
          </Col>
        </Row>
        <Row>
          <Col md={12} className="home-about-social">
            <h1 data-aos="fade-right">
              <span className="primary-header">CONNECT </span> WITH ME
            </h1>
            <p data-aos="fade-left">Feel free to connect with me</p>
            <ul className="home-about-social-links" data-aos="fade-up">
              <li className="social-icons">
                <a
                  target="_blank"
                  rel="noreferrer"
                  className="icon-colour  home-social-icons"
                  aria-label="github"
                >
                  <AiFillGithub />
                </a>
              </li>
              <li className="social-icons">
                <a
                  target="_blank"
                  rel="noreferrer"
                  className="icon-colour  home-social-icons"
                  aria-label="twitter"
                >
                  <AiOutlineTwitter />
                </a>
              </li>
              <li className="social-icons">
                <a
                  target="_blank"
                  rel="noreferrer"
                  className="icon-colour  home-social-icons"
                  aria-label="email"
                >
                  <AiOutlineMail />
                </a>
              </li>
              <li className="social-icons">
                <a
                  target="_blank"
                  rel="noreferrer"
                  className="icon-colour  home-social-icons"
                  aria-label="linkedin"
                >
                  <FaLinkedinIn />
                </a>
              </li>
              <li className="social-icons">
                <a
                  target="_blank"
                  rel="noreferrer"
                  className="icon-colour home-social-icons"
                  aria-label="instagram"
                >
                  <AiFillInstagram />
                </a>
              </li>
            </ul>
          </Col>
        </Row>
      </Container>
    </Container>
  );
}
export default Home2;