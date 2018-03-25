import React, { Component } from 'react';
import Paper from 'material-ui/Paper/Paper';
import Table, { TableBody, TableCell, TableHead, TableRow } from 'material-ui/Table';
import _ from 'lodash';
import addEllipsis from 'add-ellipsis';

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

  mapTableData(data) {
    return _.map(data, (row) => (
      <TableRow>
        <TableCell><a href={row.shortened}>{row.shortened}</a></TableCell>
        <TableCell>{row.creationDate}</TableCell>
        <TableCell><a href={row.original}>{row.original.length > 27 ? addEllipsis(row.original, 27) : row.original}</a></TableCell>
        <TableCell>{row.clicks}</TableCell>
      </TableRow>
    ));
  }

  render() {
    const styles = getStyles(this.state, this.props);
    return (
      <Paper>
      <Table>
        <TableHead>
          <TableRow>
            <TableCell>Shortened Link</TableCell>
            <TableCell>Created On</TableCell>
            <TableCell>Original Link</TableCell>
            <TableCell numeric>Total Clicks</TableCell>
          </TableRow>
        </TableHead>
        <TableBody>
          {this.mapTableData(this.props.data)}
        </TableBody>
      </Table>
    </Paper>
    );
  }
}
