import React, { Component } from 'react';
import { Button } from 'material-ui';
import Paper from 'material-ui/Paper/Paper';
import Input from 'material-ui/Input/Input';

const getStyles = (state, props) => ({
  paper: {
    ...props.barStyle
  },
  content: {
    container: {
      display: 'flex',
      padding: '4px',
      justifyContent: 'right',
      alignItems: 'bottom'
    },
    input: {
      flexGrow: 1,
      marginRight: '8px',
      marginLeft: '8px'
    }
  }
});

export default class InputBar extends Component {

  constructor(props) {
    super(props);
    this.state = {
      value: ''
    };

    this.handleKeyPressed = this.handleKeyPressed.bind(this);
  }

  componentWillReceiveProps(newProps) {
    this.setState({
      onClick: newProps.onClick,
      value: newProps.value,
      onChange: newProps.onChange
    });
  }

  onChange(evt, text) {
    this.setState({ value: text });
  }

  handleKeyPressed(e) {
    if (e.charCode === 13) {
      this.props.onClick();
    }
  }

  onClick() {}

  render() {
    const styles = getStyles(this.state, this.props);
    return (
      <Paper style={styles.paper}>
        <div style={styles.content.container}>
          <Input placeholder="Enter a URL to shorten"
            value={this.props.value || this.state.value}
            style={styles.content.input}
            autoFocus={true}
            disableUnderline={true}
            onChange={this.props.onChange || this.onChange}
            onKeyPress={this.handleKeyPressed}
          />
          <Button color='secondary' onClick={this.props.onClick || this.onClick}>Shorten</Button>
        </div>
      </Paper>
    );
  }
}
