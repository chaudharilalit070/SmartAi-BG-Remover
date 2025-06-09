// // // AppContext.js
// // import { createContext } from "react";

// // export const AppContext = createContext(null); // It's good practice to provide a default value (null or {})


// // const AppContextProvider = (props) => {

// // const backendUrl=import.meta.env.VITE_BACKEND_URL;
// // const contextValue = {
// // backendUrl
// // };

// //     return (
// //         <AppContext.Provider value={contextValue}>
// //             {props.children}
// //         </AppContext.Provider>
// //     );
// // };

// // export default AppContextProvider;



// // AppContext.jsx
// // import { useAuth, useClerk, useUser } from "@clerk/clerk-react";
// // import axios from "axios";
// // import { createContext, useState } from "react";
// // import toast from "react-hot-toast";
// // import { useNavigate } from "react-router-dom";

// // export const AppContext = createContext(null); // It's good practice to provide a default value (null or {})

// // const AppContextProvider = (props) => {
// //   const [credit, setCredit] = useState(false); // ✅ CHANGED: initialized as 0 instead of false

// //   const backendUrl = import.meta.env.VITE_BACKEND_URL;
// //   const { getToken } = useAuth();
// //   const [image,setImage]=useState(false);
// //   const [resultImage,setResultImage]=useState(false);
// //   const {isSignedIn }=useUser();
// //   const {openSignedIn}=useClerk();
// //  const navigate= useNavigate();
// //   const loadUserCredits = async () => {
// //     try {
// //       const token = await getToken();
// //       const response = await axios.get(backendUrl + "/users/credits", {
// //         headers: { Authorization: `Bearer ${token}` },
// //       });
// //       if (response.data.success) {
// //         // ✅ CHANGED: properly extract 'credits' from response.data.data
// //         setCredit(response.data.data.credits);
// //       } else {
// //         toast.error("Error loading credits");
// //       }
// //     } catch (error) {
// //       toast.error("Error loading credits");
// //     }
// //   };
// //   const removeBg = async (selectedImage) => {
// //   try {
// //     if (!isSignedIn) {
// //       return openSignedIn(); // Fixed typos: isSingedIn → isSignedIn, openSingedIn → openSignedIn
// //     }
// //     setImage(selectedImage);
// //     setResultImage(false);
// //     // Navigate to the result image
// //     navigate("/result");

// //     const token = await getToken();
// //     const formData = new FormData();
// //     if (selectedImage) {
// //       formData.append("file", selectedImage);
// //     }

// //     const { data: base64Image } = await axios.post(
// //       backendUrl + "/images/remove-background",
// //       formData,
// //       {
// //         headers: { Authorization: `Bearer ${token}` },
// //       }
// //     );

// //     setResult(`data:image/png;base64, ${base64Image}`);
// //     setCredit(credit - 1);
// //   } catch (error) {
// //     console.error(error);
// //     toast.error("Error while removing background image credits"); // Improved message clarity
// //   }
// // };

// //   const contextValue = {
// //     credit,
// //     setCredit,
// //     image,
// //     setImage,
// //     resultImage,
// //     setResultImage,
// //     backendUrl,
// //     loadUserCredits,
// //     removeBg
// //   };
// // // const removeBg=async(selectedImage)=>{
// // //   try {
// // //     if (!isSingedIn) {
// // //       return openSingedIn();
// // //     }
// // //     setImage(selectedImage);
// // //     setResultImage(false);
// // //     //navigate  to the result  image
// // //     navigate("/result");
// // //     const token=await getToken();
// // //     const formData=new FormData();
// // //     selectedImage && formData.append("file",selectedImage);


// // //     const {data:base64Image}= await axios.post(backendUrl+"/images/remove-background",formData,{
// // //         headers: { Authorization: `Bearer ${token}` }});
// // //         setResult(`data:image/png;base64, ${base64Image}`)
// // //         setCredit(credit-1);



// // //   } catch (error) {
// // //     console.error(error);
// // //     toast.error("Error while removing credits background image");

// // //   }
// // // }


// //   return (
// //     <AppContext.Provider value={contextValue}>
// //       {props.children}
// //     </AppContext.Provider>
// //   );
// // };
// import { useAuth, useClerk, useUser } from "@clerk/clerk-react";
// import axios from "axios";
// import { createContext, useState } from "react";
// import toast from "react-hot-toast";
// import { useNavigate } from "react-router-dom";

// export const AppContext = createContext(null);

// const AppContextProvider = (props) => {
//   const [credit, setCredit] = useState(0);
//   const [image, setImage] = useState(null); // changed from false
//   const [resultImage, setResultImage] = useState(null); // changed from false

//   const backendUrl = import.meta.env.VITE_BACKEND_URL;
//   const { getToken } = useAuth();
//   const { isSignedIn } = useUser();
//   const { openSignIn } = useClerk();
//   const navigate = useNavigate();

//   const loadUserCredits = async () => {
//     try {
//       const token = await getToken();
//       if (!token) throw new Error("No token found");

//       const response = await axios.get(`${backendUrl}/users/credits`, {
//         headers: { Authorization: `Bearer ${token}` },
//       });

//       if (response.data.success) {
//         setCredit(response.data.data.credits);
//       } else {
//         toast.error("Error loading credits");
//       }
//     } catch (error) {
//       toast.error("Error loading credits");
//     }
//   };

//   const removeBg = async (selectedImage) => {
//     try {
//       if (!isSignedIn) {
//         await openSignIn(); // await added
//         return;
//       }

//       setImage(selectedImage);
//       setResultImage(null);
//       navigate("/result");

//       const token = await getToken();
//       if (!token) throw new Error("No token found");

//       const formData = new FormData();
//       if (selectedImage) {
//         formData.append("file", selectedImage);
//       }

//       const { data: base64Image } = await axios.post(
//         `${backendUrl}/images/remove-background`,
//         formData,
//         {
//           headers: { Authorization: `Bearer ${token}` },
//         }
//       );

//       setResultImage(`data:image/png;base64, ${base64Image}`);
//       setCredit((prev) => prev - 1);
//     } catch (error) {
//       console.error(error);
//       toast.error("Error while removing background image credits");
//     }
//   };

//   const contextValue = {
//     credit,
//     setCredit,
//     image,
//     setImage,
//     resultImage,
//     setResultImage,
//     backendUrl,
//     loadUserCredits,
//     removeBg,
//   };

//   return (
//     <AppContext.Provider value={contextValue}>
//       {props.children}
//     </AppContext.Provider>
//   );
// };

// export default AppContextProvider;

// import { createContext, useState } from "react";
// import { useAuth, useClerk, useUser } from "@clerk/clerk-react";
// import axios from "axios";
// import toast from "react-hot-toast";
// import { useNavigate } from "react-router-dom";

// export const AppContext = createContext();

// const AppContextProvider = ({ children }) => {
//   const [credit, setCredit] = useState(false);
//   const [image, setImage] = useState(null);
//   const [resultImage, setResultImage] = useState(null);

//   const backendUrl = import.meta.env.VITE_BACKEND_URL;

//   const { getToken } = useAuth();
  
//   const { isSignedIn } = useUser();
//   const { openSignIn } = useClerk();
//   const navigate = useNavigate();

//   const contextValue={
//     backendUrl,
//       credit,
//         setCredit,
//        loadUserCredits,
//   }


//   const loadUserCredits = async () => {
//     try {
//       const token = await getToken();
//       if (!token) throw new Error("No token");

//       const response = await axios.get(backendUrl+"/users/credits", {
//         headers: { Authorization: `Bearer ${token}` },
//       });






//       if (response.data.success) {
//         setCredit(response.data.data.credits);
//       } else { 
//         toast.error("Failed to load credits"+response.data.data);
//       }
//     } catch (error) {
//       toast.error("Error loading user credits");
//     }
//   };

//   const removeBg = async (selectedImage) => {
//     try {
//       if (!isSignedIn) {
//         await openSignIn();
//         return;
//       }

//       setImage(selectedImage);
//       setResultImage(null);
//       navigate("/result");

//       const token = await getToken();
//       if (!token) throw new Error("No token");

//       const formData = new FormData();
//       formData.append("image_file", selectedImage);

//       const response = await axios.post(
//         `${backendUrl}/images/remove-background`,
//         formData,
//         {
//           headers: {
//             Authorization: `Bearer ${token}`,
//             "Content-Type": "multipart/form-data",
//           },
//         }
//       );

//       if (typeof response.data === "string") {
//         setResultImage(`data:image/png;base64,${response.data}`);
//         setCredit((prev) => prev - 1);
//       } else {
//         toast.error(response.data?.data?.message || "No credits or error");
//       }
//     } catch (error) {
//       console.error(error);
//       toast.error("Failed to remove background");
//     }
//   };

//   return (
//     <AppContext.Provider
//       value={{
//         credit,
//         setCredit,
//         image,
//         setImage,
//         resultImage,
//         setResultImage,
//         backendUrl,
//         loadUserCredits,
//         removeBg,
//         contextValue,
//       }}
//     >
//       {children}
//     </AppContext.Provider>
//   );
// };

// export default AppContextProvider;
// // 5:19 min 











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