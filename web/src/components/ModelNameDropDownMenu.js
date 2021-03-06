import React,  { Component, }from 'react'
import { FormsySelect } from 'formsy-material-ui/lib';
import MenuItem from 'material-ui/MenuItem';
import {grey500} from 'material-ui/styles/colors';
import {ActionInfoOutline} from "material-ui/svg-icons/index";
import {IconButton, Popover} from "material-ui";
import modelDescriptions from "../model-descriptions"
import FlatButton from 'material-ui/FlatButton';

class ModelNameDropDownMenu extends Component {

    constructor(props) {
        super(props);
        this.state = {
            description: modelDescriptions[props.value]['text'],
            open: false,
        };
    }

    handleTouchTap = (event) => {
        // This prevents ghost click.
        event.preventDefault();

        this.setState({
            open: true,
            anchorEl: event.currentTarget,
        });
    };

    handleRequestClose = () => {
        this.setState({
            open: false,
        });
    };

    modelChanged = (event, value) => {
        this.setState({description: modelDescriptions[value]['text']});
    };

    render() {
        const styles = {
            container: {
                display: 'flex',
                flexDirection: 'row',
            },
            descriptionButton: {
                color: grey500,
                alignSelf: "flex-end",
                marginLeft: "12px",
            },
            popover : {
                margin: 20,
                maxWidth: "300px",
            }
        };
        return (
        <div style={styles.container}>
            <FormsySelect {...this.props} onChange={this.modelChanged} name="modelName">
                {Object.keys(modelDescriptions).map((key) =>
                    <MenuItem
                        key={key}
                        value={key}
                        primaryText={modelDescriptions[key]['title']}
                    />
                )}
            </FormsySelect>
            <span style={styles.descriptionButton}>
                <FlatButton
                    onTouchTap={this.handleTouchTap}
                    icon={<ActionInfoOutline color={grey500}/>}
                />
                <Popover
                    key="popover"
                    open={this.state.open}
                    anchorEl={this.state.anchorEl}
                    anchorOrigin={{horizontal: 'right', vertical: 'top'}}
                    targetOrigin={{horizontal: 'left', vertical: 'top'}}
                    onRequestClose={this.handleRequestClose}
                >
                    <div style={styles.popover}>{this.state.description}</div>
                </Popover>
                </span>
        </div>
        )
    }
}
export default ModelNameDropDownMenu;