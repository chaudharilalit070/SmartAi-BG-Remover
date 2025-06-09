import Header from "../components/Header";
import BgRemovalSteps from "../components/BgRemovalSteps";
import BgSlider from "../components/BgSlider";
import Pricing from "../components/Pricing";
import Testimonials from "../components/Testimonials";
import TryNow from "../components/TryNOw";

const Home = () => {
    return (
        <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 py-16 font-[outfit]">
            {/* Hero section */}
            <Header />

            {/* Background Removal step section */}
            <BgRemovalSteps />

            {/* Background Removal slider section */}
            <BgSlider />

            {/* Buy credit plans section */}
               <Pricing />
            {/* User testimonials section */}
            <Testimonials />
            {/* Try now section */}
            <TryNow />
        </div>
    );
};

export default Home;
// 1:48 min