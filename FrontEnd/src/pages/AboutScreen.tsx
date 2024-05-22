import { Col, Container, Row } from 'react-bootstrap'

export function AboutScreen() {
  return (
    <Container className="my-4">
      <Row>
        <Col md={6}>
          <h2 className="text-4xl font-semibold">About Us</h2>
          <p>
            Welcome to our open-source app! We're passionate about making travel accessible to everyone, regardless of
            whether they have access to Wi-Fi. Our journey began during an internship at Info Support, where we realized
            the importance of seamless travel experiences.
          </p>
          <p>
            Our mission is simple: empower travelers by providing a reliable and efficient app that works offline.
            Whether you're exploring a new city, hiking in the wilderness, or navigating public transportation, our app
            has you covered. No more relying on spotty connections or expensive data plans. Just pure travel freedom.
          </p>
        </Col>
        <Col md={6} className="pt-5">
          <img
            src="/osta-leaders.jpg"
            alt="Team Photo"
            className="img-fluid rounded shadow"
          />
        </Col>
      </Row>
    </Container>
  )
}
