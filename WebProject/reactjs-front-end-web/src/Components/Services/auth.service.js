import axios from "axios";

const API_URL = "http://localhost:8088/api/login"; // Consider using environment variables for this URL

const checkIfAdminExists = async () => {
  try {
    const response = await axios.get(`${API_URL}/checkAdmin`); // Correct the API URL
    return response.data.exists;
  } catch (error) {
    console.error("Error checking for existing admin:", error);
    throw error; // Rethrow the error for proper error handling in the calling function
  }
};

const register = async (username, password) => {
  try {
    const adminExists = await checkIfAdminExists();

    if (adminExists) {
      console.error("An admin account already exists. Cannot create another.");
      throw new Error("An admin account already exists."); // Throw an error with a meaningful message
    }

    const response = await axios.post(`${API_URL}/register`, { // Correct the API URL
      username,
      password,
      roles: ["ROLE_ADMIN"],
    });

    return response.data;
  } catch (error) {
    console.error("Error during registration:", error);
    throw error; // Rethrow the error for proper error handling in the calling function
  }
};

const login = async (username, password) => {
  try {
    const response = await axios.post(`${API_URL}/login`, { // Correct the API URL
      username,
      password,
    });

    if (response.data.accessToken) {
      localStorage.setItem("user", JSON.stringify(response.data));
      return response.data;
    } else {
      throw new Error("Login failed. No access token received.");
    }
  } catch (error) {
    console.error("Error during login:", error);
    throw error; // Rethrow the error for proper error handling in the calling function
  }
};

const logout = () => {
  localStorage.removeItem("user");
};

const getCurrentUser = () => {
  return JSON.parse(localStorage.getItem("user"));
};

const AuthService = {
  register,
  login,
  logout,
  getCurrentUser,
};

export default AuthService;
