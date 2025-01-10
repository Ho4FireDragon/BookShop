import React from 'react';
import { Navbar, Nav, Container } from 'react-bootstrap';
import 'bootstrap/dist/css/bootstrap.min.css';

const Header = () => {
    return (
        <Navbar bg="light" expand="lg" sticky="top">
            <Container>
                <Navbar.Brand href="#home">BookStore</Navbar.Brand>
                <Navbar.Toggle aria-controls="basic-navbar-nav" />
                <Navbar.Collapse id="basic-navbar-nav">
                    <Nav className="ml-auto">
                        <Nav.Link href="#home">Trang chủ</Nav.Link>
                        <Nav.Link href="#categories">Danh mục</Nav.Link>
                        <Nav.Link href="#about">Giới thiệu</Nav.Link>
                        <Nav.Link href="#contact">Liên hệ</Nav.Link>
                    </Nav>
                </Navbar.Collapse>
            </Container>
        </Navbar>
    );
};

export default Header;
