import { createContext, useState } from "react";
import { useAuth, useClerk, useUser } from "@clerk/clerk-react";
import axios from "axios";
import toast from "react-hot-toast";
import { useNavigate } from "react-router-dom";



export const AppContext = createContext();

const AppContextProvider = ({ children }) => {


// Define all hooks
const [credit, setCredit] = useState(false);
const [image, setImage] = useState(false);
const [resultImage, setResultImage] = useState(false);

// backend
const backendUrl = import.meta.env.VITE_BACKEND_URL;
const { getToken } = useAuth();
const { isSignedIn } = useUser();
const { openSignIn } = useClerk();
const navigate = useNavigate();

// ✅ Define loadUserCredits BEFORE using it
const loadUserCredits = async () => {
  try {
    const token = await getToken();
    if (!token) throw new Error("No token");

    const response= await axios.get(
                    backendUrl+"/users/credits",
                    
                    {
                        headers: { Authorization: `Bearer ${token}` }, // ✅ Fixed: 'Beaere' → 'Bearer'
                    })
                    console.log(response);
                    


    if (response.data.success) {
      setCredit(response.data.data.credits);
    } else {
      toast.error("Failed to load credits: " + response.data.data);
    }
  } catch (error) {
    toast.error("Error loading user credits");
  }
};

// ✅ Define removeBg BEFORE using it
const removeBg = async (selectedImage) => {
  try {
    if (!isSignedIn) {
      await openSignIn();
      return;
    }

    setImage(selectedImage);
    setResultImage(false);
    navigate("/result");

    const token = await getToken();
    if (!token) throw new Error("No token");

    const formData = new FormData();
   selectedImage && formData.append("file", selectedImage);

    const {data:base64Image} = await axios.post(
      backendUrl+"/images/remove-background",
      formData,
      {
        headers: {
          Authorization: `Bearer ${token}`,
         
        },  
      }
    );

         setResultImage(`data:image/png;base64,${base64Image}`);

      setCredit(credit-1);
   
  } catch (error) {
    console.error(error);
    toast.error("Failed to remove background");
  }
};

// ✅ Now it's safe to use in context
const contextValue = {
  credit,
  setCredit,
  image,
  setImage,
  resultImage,
  setResultImage,
  backendUrl,
  loadUserCredits,
  removeBg,
};

return (
  <AppContext.Provider value={contextValue}>
    {children}
  </AppContext.Provider>
);
}

export default AppContextProvider;
