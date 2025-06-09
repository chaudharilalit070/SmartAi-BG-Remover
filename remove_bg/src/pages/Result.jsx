// import { useContext } from "react";
// import { AppContext } from "../context/AppContext";
// import money from '../assets/money.png';

// const Result=()=>{
//     const {image,resultImage}=useContext(AppContext);
//     return (
//         <div className="mx-4 my-3 lg:mx-44 mt-14 min-h-[75vh]">
//           {/* image container */}
//            <div className="grid grid-cols-1 md:grid-cols-2 gap-8">
//             {/* Left -side */}
//             <div className="flex flex-col">
//                 <p className="font-semibold text-gray-600 mb-2">
//                     Original
//                 </p>
//                 <img src={money} alt="money" className="rounded-md w-full object-conver"  />
//             </div>

//             {/* Right -side */}
//             <div className="flex flex-col">
//             <p className="semibold text-gray-600 mb-2">
//                 Background Remove
//             </p>
//             <div className="rounded-md border border-gray-300 h-full bg-layer relative-overflow-hidden">
//                 <img src={money} alt="" className="w-full object-cover" />
//                 {
//                     !resultImage && image &&(
//                         <div className="absolute top-1/2 left-1/2 transform -translate-x-1/2 translate-y-1/2">
//                             <div className="border-4 border-indigo-600 rounded-full h-12 w-12 border-t-transparent animate-spin">

//                             </div>
//                         </div>
//                     )
//                 }
//             </div>

//             </div>
//            </div>

//            {/* Buttons */}
//            {resultImage &&(
//             <div className="flex justify-center sm:justify-end items-center flex-wrap gap-4 mt-6">
//                 <button className="border text-indigo-600 font-semibold py-2 px-4 rounded-full text-lg hover:scale-105 transition-all duration-300">
//                     Try another image
//                 </button>
//                 <a href={resultImage} download className="corser-pointer py-3 px-6 text-center text-white font-semibold rounded-full bg-gradient-to-r from-purple-500 to-indigo-500 shadow-lg hover:from-purple-600 hover:to-indigo-600 transition duration-300  ease-in-out transfrom hover:scale-105">
//                     Dwnload image
//                 </a>
//             </div>
//            )

//            }
//         </div>

//     )
// }

// export default Result;


// import { useContext } from "react";
// import { AppContext } from "../context/AppContext";
// import money from "../assets/money.png";

// const Result = () => {
//   const { image, resultImage } = useContext(AppContext);

//   return (
//     <div className="mx-4 my-3 lg:mx-44 mt-14 min-h-[75vh]">
//       {/* Image container */}
//       <div className="grid grid-cols-1 md:grid-cols-2 gap-8">
//         {/* Left side */}
//         <div className="flex flex-col">
//           <p className="font-semibold text-gray-600 mb-2">Original</p>
//           <img
//             src={image? URL.createObjectURL(image):""}
//             alt="money"
//             className="rounded-md w-full object-cover shadow-md"
//           />
//         </div>

//         {/* Right side */}
//         <div className="flex flex-col">
//           <p className="font-semibold text-gray-600 mb-2">Background Removed</p>
//           <div className="relative overflow-hidden rounded-md border border-gray-300 h-full bg-white shadow-md">
//             <img
//               src={resultImage ?resultImage:""}
//               alt="result"
//               className="w-full object-cover"
//             />
//             {!resultImage && image && (
//               <div className="absolute inset-0 flex items-center justify-center bg-white bg-opacity-75">
//                 <div className="border-4 border-indigo-600 rounded-full h-12 w-12 border-t-transparent animate-spin"></div>
//               </div>
//             )}
//           </div>
//         </div>
//       </div>

//       {/* Buttons */}
//       {resultImage && (
//         <div className="flex justify-center sm:justify-end items-center flex-wrap gap-4 mt-6">
//           <button className="border border-indigo-600 text-indigo-600 font-semibold py-2 px-4 rounded-full text-lg hover:scale-105 transition-transform duration-300">
//             Try another image
//           </button>
//           <a
//             href={resultImage}
//             download
//             className="cursor-pointer py-3 px-6 text-center text-white font-semibold rounded-full bg-gradient-to-r from-purple-500 to-indigo-500 shadow-lg hover:from-purple-600 hover:to-indigo-600 transition-transform duration-300 transform hover:scale-105"
//           >
//             Download image
//           </a>
//         </div>
//       )}
//     </div>
//   );
// };

// export default Result;


// import { useContext } from "react";
// import { AppContext } from "../context/AppContext";
// import money from "../assets/money.png";
// import { useNavigate } from "react-router-dom";

// const Result = () => {
//   const { image, resultImage } = useContext(AppContext);
//   const navigate=useNavigate();

//   // Create object URL safely for the original image if it exists
//   const originalImageUrl = image ? URL.createObjectURL(image) : null;

//   return (
//     <div className="mx-4 my-3 lg:mx-44 mt-14 min-h-[75vh]">
//       <div className="grid grid-cols-1 md:grid-cols-2 gap-8">
//       {/* Image container */}
//       <div className="grid grid-cols-1 md:grid-cols-2 gap-8">
//         {/* Left side */}
//         <div className="flex flex-col">
//           <p className="font-semibold text-gray-600 mb-2">Original</p>
//           {originalImageUrl ? (
//             <img
//               src={originalImageUrl}
//               alt="Original"
//               className="rounded-md w-full object-cover shadow-md"
//               onLoad={() => URL.revokeObjectURL(originalImageUrl)} // clean up URL after image loads
//             />
//           ) : (
//             <p>No original image selected</p>
//           )}
//         </div>

//         {/* Right side */}
//         <div className="flex flex-col">
//           <p className="font-semibold text-gray-600 mb-2">Background Removed</p>
//           <div className="relative overflow-hidden rounded-md border border-gray-300 h-full bg-white shadow-md min-h-[300px]">
//             <img
//                 src={resultImage}
//                 alt="Result"
//                 className="w-full object-cover"
//               />
//             {resultImage ? (

//             ) : image ? (
//               <div className="absolute inset-0 flex items-center justify-center bg-white bg-opacity-75">
//                 <div className="border-4 border-indigo-600 rounded-full h-12 w-12 border-t-transparent animate-spin"></div>
//               </div>
//             ) : (
//               <p className="text-center p-4 text-gray-500">No result image yet</p>
//             )}
//           </div>
//         </div>
//       </div>

//       {/* Buttons */}
//       {resultImage && (
//         <div className="flex justify-center sm:justify-end items-center flex-wrap gap-4 mt-6">
//           <button className="border border-indigo-600 text-indigo-600 font-semibold py-2 px-4 rounded-full text-lg hover:scale-105 transition-transform duration-300 " onClick={()=>navigate("/")}>
//             Try another image
//           </button>
//           <a
//             href={resultImage}
//             download
//             className="cursor-pointer py-3 px-6 text-center text-white font-semibold rounded-full bg-gradient-to-r from-purple-500 to-indigo-500 shadow-lg hover:from-purple-600 hover:to-indigo-600 transition-transform duration-300 transform hover:scale-105"
//           >
//             Download image
//           </a>
//         </div>
//       )}
//       </div>
//     </div>
//   );
// };

// export default Result;
// 7:26 min


// import { useContext } from "react";
// import { AppContext } from "../context/AppContext";
// import { useNavigate } from "react-router-dom";
// import { assets } from "../assets/assets";
//
// const Result = () => {
//   const { image, resultImage } = useContext(AppContext);
//   return (
//     <div class="mt-14 mb-3 mx-4 lg:mx-44 min-h-[75vh]">
//       {/* image container */}
//       <div className="grid grid-cols-1 md:grid-cols-2 gap-8">
//         {/* left side */}
//         <div className="flex flex-col space-y-4">
//           <p className="font-semibold text-gray-600 mb-2">Original</p>
//           <img
//             src={assets.imgslider}
//             alt="images"
//             className="rounded-md w-full object-cover shadow-md"
//           />
//         </div>
//
//         {/* Right side */}
//         <div className="flex flex-col space-y-4">
//           <p className="font-semibold text-gray-600 mb-2">
//             Background Removed
//           </p>
//
//           <div className="relative rounded-md border border-gray-300 h-full bg-layer overflow-hidden">
//             <img
//               src={assets.imgslider2}
//               alt="images"
//               className="w-full object-cover shadow-md"
//             />
//
//             {!resultImage && image && (
//               <div className="absolute top-1/2 left-1/2 transform -translate-x-1/2 -translate-y-1/2">
//                 <div className="border-4 border-indigo-600 rounded-full h-12 w-12 border-t-transparent animate-spin"></div>
//               </div>
//             )}
//           </div>
//         </div>
//
//
//       </div>
// {/* button */}
//
//
// {resultImage &&
// (
//   <div className="flex justify-center sm:justify-end items-center flex-wrap gap-4 mt-6">
//             <button className="border border-indigo-600 text-indigo-600 font-semibold py-2 px-4 rounded-full text-lg hover:scale-105 transition-all duration-300 " onClick={()=>navigate("/")}>
//              Try another image
//            </button>
//
//   </div>
// )}
//        <a
//             href={resultImage}
//             download
//             className="cursor-pointer py-3 px-6 text-center text-white font-semibold rounded-full bg-gradient-to-r from-purple-500 to-indigo-500 shadow-lg hover:from-purple-600 hover:to-indigo-600 transition duration-300  ease-in-out transform transform hove r:scale-105"
//           >
//             Download image
//           </a>
//     </div>
//
//   )
// }

import { useContext } from "react";
import { AppContext } from "../context/AppContext";
import { useNavigate } from "react-router-dom";
import { assets } from "../assets/assets";

const Result = () => {
  const navigate = useNavigate();
  const { image, resultImage } = useContext(AppContext);

  return (
    <div className="mt-14 mb-3 mx-4 lg:mx-44 min-h-[75vh]">
      {/* image container */}
      <div className="grid grid-cols-1 md:grid-cols-2 gap-8">
        {/* left side */}
        <div className="flex flex-col space-y-4">
          <p className="font-semibold text-gray-600 mb-2">Original</p>
          <img
            src={image ? URL.createObjectURL(image) : ""}
            alt="original"
            className="rounded-md w-full object-cover shadow-md"
          />
        </div>

        {/* right side */}
        <div className="flex flex-col space-y-4">
          <p className="font-semibold text-gray-600 mb-2">Background Removed</p>
          <div className="relative rounded-md border border-gray-300 h-full bg-layer overflow-hidden">
            <img
              src={resultImage ? resultImage : ""}
              alt="result"
              className="w-full object-cover shadow-md"
            />

            {!resultImage && image && (
              <div className="absolute top-1/2 left-1/2 transform -translate-x-1/2 -translate-y-1/2">
                <div className="border-4 border-indigo-600 rounded-full h-12 w-12 border-t-transparent animate-spin"></div>
              </div>
            )}
          </div>
        </div>
      </div>

      {/* buttons */}
      {resultImage && (
        <div className="flex justify-center sm:justify-end items-center flex-wrap gap-4 mt-6">
          <button
            className="border border-indigo-600 text-indigo-600 font-semibold py-2 px-4 rounded-full text-lg hover:scale-105 transition-all duration-300"
            onClick={() => navigate("/")}
          >
            Try another image
          </button>

          <a
            href={resultImage}
            download
            className="cursor-pointer py-3 px-6 text-white font-semibold rounded-full bg-gradient-to-r from-purple-500 to-indigo-500 shadow-lg hover:from-purple-600 hover:to-indigo-600 transition duration-300 ease-in-out transform hover:scale-105"
          >
            Download image
          </a>
        </div>
      )}
    </div>
  );
};

export default Result;
