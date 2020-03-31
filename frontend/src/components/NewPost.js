import React,{Component} from "react";
import AppNavbar from './AppNavBar'
import { Button, Container, Form, FormGroup, Input, Label } from 'reactstrap';
import {Redirect} from "react-router-dom";
import Posts from "./posts";
class NewPost extends Component{

    post={
        user:'',
        text:'',
        redirectToList:false
    }


    constructor(props) {
        super(props);
        this.state = {post: this.post};
        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleChange(event) {
        const target = event.target;
        const value = target.value;
        const name = target.name;
        let post = {...this.state.post};
        post[name] = value;
        this.setState({post});
    }

    async handleSubmit(event) {
        event.preventDefault();
        const {post} = this.state;

         await fetch('/post?user='+post.user+'&text='+post.text, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
        });
        this.setState(()=> ({redirectToList: true}))
    }

    render() {
        const {post} = this.state
        const{redirectToList} =this.state
        if(redirectToList === true){
            return <Redirect to='/' exact={true} component={Posts}/>
        }

        return  <div>
            <AppNavbar/>
            <Container>
                <h1 align='center'>Adicionar Post</h1>
                <Form onSubmit={this.handleSubmit}>
                    <div>
                    <FormGroup>
                        <Label for="user">Usuário:</Label>
                        <Input placeholder="Nome do Usuário" type="text" name="user" id="user" value={post.user || ''}
                               onChange={this.handleChange}/>
                    </FormGroup>
                    <FormGroup>
                        <Label for="text">Texto:</Label>
                        <Input placeholder="Texto do Post" type="text" name="text" size={300} id="text" value={post.text || ''}
                               onChange={this.handleChange}/>
                    </FormGroup>
                    </div>
                    <FormGroup>
                        <Button color="primary" type="submit">Salvar</Button>{' '}
                    </FormGroup>
                </Form>
            </Container>
        </div>;
    }
} export default NewPost;