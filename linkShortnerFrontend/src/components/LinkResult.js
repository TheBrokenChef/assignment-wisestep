import axios from "axios";
import { useEffect, useState } from "react"
import CopyToClipboard from "react-copy-to-clipboard";

const LinkResult = ({ inputValue }) => {
    const [shortenLink, setShortenLink] = useState("");
    const [copied, setCopied] = useState(false);
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState(false);

    const fetchData = async () => {
        try {
            setLoading(true);
            const res = await axios(`http://localhost:8080/generate?link=${inputValue}`);
            const link = res.data.shortLink
            setShortenLink(link);
        } catch(err) {
            setError(err);
        } finally {
            setLoading(false);
        }
    }

    useEffect(() => {
        if(inputValue.length) {
            fetchData();
        }
    }, [inputValue]);

    useEffect(() => {
        const timer = setTimeout(() => {
        setCopied(false);
        }, 1000);

        return () => clearTimeout(timer);
    }, [copied]);

    if(loading) {
        return <p className="noData">Loading...</p>
    }
    if(error) {
        return <p className="noData">Something went wrong :(</p>
    }


    return (
        <>
        {shortenLink && (
            <div className="result">
            <p>{shortenLink}</p>
            <CopyToClipboard
                text={shortenLink}
                onCopy={() => setCopied(true)}
            >
                <button className={copied ? "copied" : ""}>Copy to Clipboard</button>
            </CopyToClipboard>
            </div>
        )}
        </>
    )
}

export default LinkResult