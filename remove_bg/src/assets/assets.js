import logo from './logo.png';
import banner from './banner.mp4';
import { Quote } from 'lucide-react';
import money from './money.png';
import imgslider from "./imgslider.png";
import imgslider2 from "./imgslider2.png";

export const assets = {
  logo,//menubar
  banner,//header
  money,
  imgslider2,
  imgslider,

};




// inside bgremover
export const steps = [
  {
    step: "Step 1",
    title: "📸 Select Your Image",
    description: `Upload the image you want to edit by clicking the "Start" button. We support popular formats like PNG and JPG. Ensure your photo is clear and well-lit for the best background removal. Any image size or orientation is supported — portrait, landscape, or square!`,
  },
  {
    step: "Step 2",
    title: "✨ Let Our AI Work Its Magic",
    description: `Our powerful AI automatically detects and removes the background in seconds. No need to select or crop manually — it’s all done for you. Even tricky edges like hair, shadows, and transparent areas are handled flawlessly.`,
  },
  {
    step: "Step 3",
    title: "⬇️ Download Your Image",
    description: `Preview your new image with the background removed. Satisfied with the result? Click "Download" to save it in high resolution. Perfect for social media, product photos, presentations, and creative projects.`,
  },
];

// bgslider
export const category=["People","Products","Animals","Cars","Graphics"]

//bgprice
export const plans = [
  {
    id: "Basic",
    name: "Basic Package",
    price: 499,
    credits: "100 credits",
    description: "Best for personal use",
    popular: false
  },
  {
    id: "primium",
    name: "primium Package",
    price: 899,
    credits: "250 credits",
    description: "Best for Business use",
    popular: true
  },
  {
    id: "ultimate",
    name: "ultimate Package",
    price: 1499,
    credits: "10000 credits",
    description: "Best for Business use",
    popular: true
  }
];


// Testimonials
export const testimonials = [
  {
    id: 1,
    quote: "We are impressed by the AI and think it's the best choice on the market.",
    author: "Anthony Walker",
    handle: "@_webChitect",
  },
  {
    id: 2,
    quote: "This product has drastically improved our workflow and creativity.",
    author: "Sara Thompson",
    handle: "@sara.codes",
  },
  {
    id: 3,
    quote: "The user experience is outstanding — smooth, fast, and intuitive.",
    author: "David Kim",
    handle: "@david_uiux",
  },
  
];


// footer
export const footer = [
  {
    url: "http://facebook.com",
    logo: "https://img.icons8.com/fluent/30/000000/facebook-new.png",
  },
  {
    url: "http://instagram.com",
    logo: "https://img.icons8.com/fluent/30/000000/instagram-new.png",
  },
  {
    url: "http://twitter.com",
    logo: "https://img.icons8.com/fluent/30/000000/twitter.png",
  },
  {
    url: "http://linkedin.com",
    logo: "https://img.icons8.com/fluent/30/000000/linkedin.png",
  },
];
// 2:40 min