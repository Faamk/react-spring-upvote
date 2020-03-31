import React, {Component} from 'react'
import AppNavbar from "./AppNavBar";
import {Button, CardText, Col, Spinner} from "reactstrap";
import Moment from 'moment';
import {blue} from "color-name";

class Posts extends Component {


    constructor(props) {
        super(props);
        this.state = {posts: [], isLoading: true};
        this.upvote = this.upvote.bind(this)
    }

    componentDidMount() {
        this.setState({isLoading: true});
        fetch('/posts')
            .then(res => res.json())
            .then((data) => {
                this.setState({posts: data, isLoading: false})
            })
            .catch(console.log)
    }

    async upvote(id) {
        const requestOptions = {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
        };
        await fetch('/upvote?id=' + id, requestOptions)
            .catch(reason => console.log(reason))
            .then(() => {
                let json = this.state.posts
                for (var k = 0; k < json.length; ++k) {
                    if (id === json[k]['id']) {
                        json[k]['upvotes'] = json[k]['upvotes'] + 1;
                    }
                }
                this.setState({posts: json})
            })

    }

    render() {
        const {posts, isLoading} = this.state;

        if (isLoading) {
            return (<div>
                    <AppNavbar/>
                    <Spinner color="primary" />
            </div>
            )
        }
        if (posts.length===0) {
            return (<div>
                    <AppNavbar/>
                    <p>Sem Posts para mostrar :(</p>
                </div>
            )
        }
        else {
            const postlist = posts.map((post,index) => (
                <div className="card" key={index}>
                    <div className="card-body">
                        <h5 >{post.user}</h5>
                        <label >às {Moment(post.date).format(' h:mm:ss a, DD/MM/YYYY')}:</label>
                        <Col sm="6">
                        <card  body style={{backgroundColor:'#878787'}}>
                            <CardText style={{ color: 'white' }}>{post.text}</CardText>
                        </card>
                        </Col>
                        <h5>Upvotes: {post.upvotes}</h5>
                        <Button onClick={() => this.upvote(post.id)}>Upvote</Button>
                    </div>
                </div>
            ))

            return (<div>
                    <AppNavbar/>
                    {postlist}
                </div>
            )
        }


    }
}

export default Posts;


