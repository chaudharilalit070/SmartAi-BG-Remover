// const { useAuth, useUser } = require("@clerk/clerk-react");
// const { useState, useEffect, useContext } = require("react");
// const { AppContext } = require("../context/AppContext");
// const { default: axios } = require("axios");
// const { default: toast } = require("react-hot-toast");

// const UserSyncHandler=()=>{
// const {isLoaded,isSingedIn,getToken}=useAuth();
// const {user}=useUser();
// const [synced ,setSynced]=useState(false);
// const {backendUrl} =useContext(AppContext)
// useEffect(()=>{

//     const saveUser=async()=>{
//         if (!isLoaded|| !isSingedIn||synced) {
//             return;

//         }
//         try {
//           const token=await getToken();
//           const userData={
//             clerkId:user.id,
//             email:user.primaryEmailAddress.emailAddress,
//             firstName:user.firstName,
//             lastName:user.lastName,


//           };
// const response= axios.post(backendUrl+"/users",userData,{headers:{"Authorization":`Beaere ${token}`}});         
// if (response.data.success === true ) {
//     console.log("user successfuly created !");
//     toast.success("user successfuly created !")

// }
// else{
// toast.error("user sycn feild. please try again")

// }
// setSynced(true)//prevent re-posting
//         } catch (error) {
//             console.error("user sycn feild",error);
//             toast.error("unable to create account. please try again")


//         }
//     }
//     saveUser(); 
// },[isLoaded,isSingedIn,getToken,user,synced]);


//     return null;
// }

// export default UserSyncHandler;


import { useAuth, useUser } from "@clerk/clerk-react";
import { useState, useEffect, useContext } from "react";
import { AppContext } from "../context/AppContext";
import axios from "axios";
import toast from "react-hot-toast";

const UserSyncHandler = () => {
    const { isLoaded, isSignedIn, getToken } = useAuth(); // ✅ Fixed: isSingedIn → isSignedIn
    const { user } = useUser();
    const [synced, setSynced] = useState(false);
    const { backendUrl, loadUserCredits } = useContext(AppContext);

    useEffect(() => {
        const saveUser = async () => {
            if (!isLoaded || !isSignedIn || synced) {
                return;
            }

            try {
                const token = await getToken();
                const userData = {
                    clerkId: user.id,
                    email: user.primaryEmailAddress.emailAddress,
                    firstName: user.firstName,
                    lastName: user.lastName,
                     photoUrl:user.imageUrl
                };
                 await axios.post(
                    backendUrl+"/users",
                    userData,
                    {
                        headers: { Authorization: `Bearer ${token}` }, // ✅ Fixed: 'Beaere' → 'Bearer'
                    }
                );








                

                setSynced(true); // ✅ Prevent re-posting
                await loadUserCredits();
                // TODO update the user credits 
            } catch (error) {
                console.error("User sync failed", error);
                toast.error("Unable to create account. Please try again.");
            }
        };

        saveUser();
    }, [isLoaded, isSignedIn, getToken, user, synced]);

    return null;
};

export default UserSyncHandler;


//7:2 min