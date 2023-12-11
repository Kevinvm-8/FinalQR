import React, { useState, useEffect, useCallback } from 'react';
import styled from 'styled-components';

const ReportContainer = styled.div`
  background-color: #fff;
  border: 1px solid #ccc;
  border-radius: 5px;
  padding: 20px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  max-width: 100000px;
  margin: 140px auto;
`;

const Title = styled.h2`
  color: #333;
  text-align: center;
`;

const Table = styled.table`
  width: 100%;
  border-collapse: collapse;
  margin-top: 20px;
`;

const TableHead = styled.thead`
  background-color: #007bff;
  color: #fff;
`;

const TableRow = styled.tr`
  &:nth-child(even) {
    background-color: #f2f2f2;
  }
`;

const TableCell = styled.td`
  padding: 10px;
  text-align: center;
  word-break: break-all;
`;

const ActionButton = styled.button`
  background-color: ${(props) => (props.danger ? '#dc3545' : '#28a745')};
  color: #fff;
  border: none;
  padding: 5px 10px;
  border-radius: 5px;
  cursor: pointer;
  font-size: 14px;
  margin-right: 5px;

  &:hover {
    background-color: ${(props) => (props.danger ? '#bd2130' : '#218838')};
  }
`;

const API_GET_URL_REPORT = '/api/report-url-list';
const API_BASE_URL_REPORT = '/api/url-report';

const Report = () => {
  const [reportList, setReportList] = useState([]);

  const fetchData = useCallback(async () => {
    try {
      const response = await fetch(API_GET_URL_REPORT);

      if (!response.ok) {
        throw new Error('Failed to fetch data');
      }

      const data = await response.json();
      setReportList(data);
    } catch (error) {
      console.error('Error fetching data:', error.message);
    }
  }, []);

  useEffect(() => {
    fetchData();
  }, [fetchData]);

  const handleAccept = async (id, type) => {
    try {
      let apiUrl;
      let status;

      if (type === 'whitelist') {
        apiUrl = '/api/url-Whitelist';
        status = 'whitelist';
      } else if (type === 'blacklist') {
        apiUrl = '/api/url-blacklist';
        status = 'blacklist';
      } else {
        console.error('Invalid accept type');
        return;
      }

      const response = await fetch(apiUrl, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({ id, status }),
      });

      if (!response.ok) {
        throw new Error(`Failed to accept URL: ${response.statusText}`);
      }

      // Wait for the data to be updated before proceeding
      await setReportList((prevUrlList) => prevUrlList.filter((item) => item.id !== id));
    } catch (error) {
      console.error('Error accepting URL:', error.message);
    }
  };

  


  const handleDelete = async (id) => {
    try {
      const apiUrl = `${API_BASE_URL_REPORT}/${id}`;

      const response = await fetch(apiUrl, {
        method: 'DELETE',
        headers: {
          'Content-Type': 'application/json',
        },
      });

      if (!response.ok) {
        throw new Error(`Failed to delete URL: ${response.statusText}`);
      }

      setReportList((prevUrlList) => prevUrlList.filter((item) => item.id !== id));
    } catch (error) {
      console.error('Error deleting URL:', error.message);
    }
  };

  return (
    <ReportContainer>
      <Title>Check Notification</Title>
      <Table>
        <TableHead>
          <tr>
            <th style={{ width: '80%', textAlign: 'center' }}>URL</th>
            <th style={{ width: '20%', textAlign: 'center' }}>Actions</th>
          </tr>
        </TableHead>
        <tbody>
          {reportList.map((item) => (
            <TableRow key={item.id}>
              <TableCell>{item.urlReport}</TableCell>
              <TableCell>
                <ActionButton onClick={() => handleDelete(item.id)} danger>
                  Delete
                </ActionButton>
              </TableCell>
            </TableRow>
          ))}
        </tbody>
      </Table>
    </ReportContainer>
  );
};

export default Report;
